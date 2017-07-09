import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Seen } from './seen.model';
import { SeenPopupService } from './seen-popup.service';
import { SeenService } from './seen.service';
import { Customer, CustomerService } from '../customer';
import { Products, ProductsService } from '../products';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-seen-dialog',
    templateUrl: './seen-dialog.component.html'
})
export class SeenDialogComponent implements OnInit {

    seen: Seen;
    authorities: any[];
    isSaving: boolean;

    customers: Customer[];

    products: Products[];
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private seenService: SeenService,
        private customerService: CustomerService,
        private productsService: ProductsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.productsService
            .query({filter: 'seen-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.seen.productsId) {
                    this.products = res.json;
                } else {
                    this.productsService
                        .find(this.seen.productsId)
                        .subscribe((subRes: Products) => {
                            this.products = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.seen.id !== undefined) {
            this.subscribeToSaveResponse(
                this.seenService.update(this.seen), false);
        } else {
            this.subscribeToSaveResponse(
                this.seenService.create(this.seen), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Seen>, isCreated: boolean) {
        result.subscribe((res: Seen) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Seen, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Seen is created with identifier ${result.id}`
            : `A Seen is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'seenListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }

    trackProductsById(index: number, item: Products) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-seen-popup',
    template: ''
})
export class SeenPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private seenPopupService: SeenPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.seenPopupService
                    .open(SeenDialogComponent, params['id']);
            } else {
                this.modalRef = this.seenPopupService
                    .open(SeenDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

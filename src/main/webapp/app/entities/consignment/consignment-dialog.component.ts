import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Consignment } from './consignment.model';
import { ConsignmentPopupService } from './consignment-popup.service';
import { ConsignmentService } from './consignment.service';
import { Products, ProductsService } from '../products';
import { Storage, StorageService } from '../storage';
import { Producers, ProducersService } from '../producers';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-consignment-dialog',
    templateUrl: './consignment-dialog.component.html'
})
export class ConsignmentDialogComponent implements OnInit {

    consignment: Consignment;
    authorities: any[];
    isSaving: boolean;

    products: Products[];

    storages: Storage[];

    producers: Producers[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private consignmentService: ConsignmentService,
        private productsService: ProductsService,
        private storageService: StorageService,
        private producersService: ProducersService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.productsService.query()
            .subscribe((res: ResponseWrapper) => { this.products = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.storageService.query()
            .subscribe((res: ResponseWrapper) => { this.storages = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.producersService.query()
            .subscribe((res: ResponseWrapper) => { this.producers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.consignment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.consignmentService.update(this.consignment));
        } else {
            this.subscribeToSaveResponse(
                this.consignmentService.create(this.consignment));
        }
    }

    private subscribeToSaveResponse(result: Observable<Consignment>) {
        result.subscribe((res: Consignment) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Consignment) {
        this.eventManager.broadcast({ name: 'consignmentListModification', content: 'OK'});
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

    trackProductsById(index: number, item: Products) {
        return item.id;
    }

    trackStorageById(index: number, item: Storage) {
        return item.id;
    }

    trackProducersById(index: number, item: Producers) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-consignment-popup',
    template: ''
})
export class ConsignmentPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consignmentPopupService: ConsignmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.consignmentPopupService
                    .open(ConsignmentDialogComponent, params['id']);
            } else {
                this.modalRef = this.consignmentPopupService
                    .open(ConsignmentDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Comments } from './comments.model';
import { CommentsPopupService } from './comments-popup.service';
import { CommentsService } from './comments.service';
import { Customer, CustomerService } from '../customer';
import { Products, ProductsService } from '../products';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-comments-dialog',
    templateUrl: './comments-dialog.component.html'
})
export class CommentsDialogComponent implements OnInit {

    comments: Comments;
    authorities: any[];
    isSaving: boolean;

    customers: Customer[];

    products: Products[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private commentsService: CommentsService,
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
        this.productsService.query()
            .subscribe((res: ResponseWrapper) => { this.products = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.comments.id !== undefined) {
            this.subscribeToSaveResponse(
                this.commentsService.update(this.comments), false);
        } else {
            this.subscribeToSaveResponse(
                this.commentsService.create(this.comments), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Comments>, isCreated: boolean) {
        result.subscribe((res: Comments) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Comments, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Comments is created with identifier ${result.id}`
            : `A Comments is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'commentsListModification', content: 'OK'});
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
    selector: 'jhi-comments-popup',
    template: ''
})
export class CommentsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private commentsPopupService: CommentsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.commentsPopupService
                    .open(CommentsDialogComponent, params['id']);
            } else {
                this.modalRef = this.commentsPopupService
                    .open(CommentsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Comments } from './comments.model';
import { CommentsPopupService } from './comments-popup.service';
import { CommentsService } from './comments.service';
import { CustomerRoom, CustomerRoomService } from '../customer-room';
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

    customerrooms: CustomerRoom[];

    products: Products[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private commentsService: CommentsService,
        private customerRoomService: CustomerRoomService,
        private productsService: ProductsService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.customerRoomService.query()
            .subscribe((res: ResponseWrapper) => { this.customerrooms = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
                this.commentsService.update(this.comments));
        } else {
            this.subscribeToSaveResponse(
                this.commentsService.create(this.comments));
        }
    }

    private subscribeToSaveResponse(result: Observable<Comments>) {
        result.subscribe((res: Comments) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Comments) {
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

    trackCustomerRoomById(index: number, item: CustomerRoom) {
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

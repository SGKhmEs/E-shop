import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Bucket } from './bucket.model';
import { BucketPopupService } from './bucket-popup.service';
import { BucketService } from './bucket.service';
import { Manager, ManagerService } from '../manager';
import { AddressShipping, AddressShippingService } from '../address-shipping';
import { Customer, CustomerService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-bucket-dialog',
    templateUrl: './bucket-dialog.component.html'
})
export class BucketDialogComponent implements OnInit {

    bucket: Bucket;
    authorities: any[];
    isSaving: boolean;

    managers: Manager[];

    addressshippings: AddressShipping[];

    customers: Customer[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private bucketService: BucketService,
        private managerService: ManagerService,
        private addressShippingService: AddressShippingService,
        private customerService: CustomerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.managerService.query()
            .subscribe((res: ResponseWrapper) => { this.managers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.addressShippingService
            .query({filter: 'bucket-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.bucket.addressShipping || !this.bucket.addressShipping.id) {
                    this.addressshippings = res.json;
                } else {
                    this.addressShippingService
                        .find(this.bucket.addressShipping.id)
                        .subscribe((subRes: AddressShipping) => {
                            this.addressshippings = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.bucket.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bucketService.update(this.bucket), false);
        } else {
            this.subscribeToSaveResponse(
                this.bucketService.create(this.bucket), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Bucket>, isCreated: boolean) {
        result.subscribe((res: Bucket) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Bucket, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Bucket is created with identifier ${result.id}`
            : `A Bucket is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'bucketListModification', content: 'OK'});
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

    trackManagerById(index: number, item: Manager) {
        return item.id;
    }

    trackAddressShippingById(index: number, item: AddressShipping) {
        return item.id;
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-bucket-popup',
    template: ''
})
export class BucketPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bucketPopupService: BucketPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.bucketPopupService
                    .open(BucketDialogComponent, params['id']);
            } else {
                this.modalRef = this.bucketPopupService
                    .open(BucketDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

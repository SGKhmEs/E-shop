import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Address } from './address.model';
import { AddressPopupService } from './address-popup.service';
import { AddressService } from './address.service';
import { Customer, CustomerService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-address-dialog',
    templateUrl: './address-dialog.component.html'
})
export class AddressDialogComponent implements OnInit {

    address: Address;
    authorities: any[];
    isSaving: boolean;

    customers: Customer[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private addressService: AddressService,
        private customerService: CustomerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.customerService
            .query({filter: 'address-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.address.customerId) {
                    this.customers = res.json;
                } else {
                    this.customerService
                        .find(this.address.customerId)
                        .subscribe((subRes: Customer) => {
                            this.customers = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.address.id !== undefined) {
            this.subscribeToSaveResponse(
                this.addressService.update(this.address), false);
        } else {
            this.subscribeToSaveResponse(
                this.addressService.create(this.address), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Address>, isCreated: boolean) {
        result.subscribe((res: Address) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Address, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Address is created with identifier ${result.id}`
            : `A Address is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'addressListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-address-popup',
    template: ''
})
export class AddressPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private addressPopupService: AddressPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.addressPopupService
                    .open(AddressDialogComponent, params['id']);
            } else {
                this.modalRef = this.addressPopupService
                    .open(AddressDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

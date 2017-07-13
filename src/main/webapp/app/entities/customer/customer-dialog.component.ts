import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Customer } from './customer.model';
import { CustomerPopupService } from './customer-popup.service';
import { CustomerService } from './customer.service';
import { LoginOptions, LoginOptionsService } from '../login-options';
import { Address, AddressService } from '../address';
import { PersonalInformation, PersonalInformationService } from '../personal-information';
import { Avatar, AvatarService } from '../avatar';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-customer-dialog',
    templateUrl: './customer-dialog.component.html'
})
export class CustomerDialogComponent implements OnInit {

    customer: Customer;
    authorities: any[];
    isSaving: boolean;

    loginoptions: LoginOptions[];

    addresses: Address[];

    personalinfos: PersonalInformation[];

    avatars: Avatar[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private customerService: CustomerService,
        private loginOptionsService: LoginOptionsService,
        private addressService: AddressService,
        private personalInformationService: PersonalInformationService,
        private avatarService: AvatarService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.loginOptionsService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.loginOptionsId) {
                    this.loginoptions = res.json;
                } else {
                    this.loginOptionsService
                        .find(this.customer.loginOptionsId)
                        .subscribe((subRes: LoginOptions) => {
                            this.loginoptions = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.addressService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.addressId) {
                    this.addresses = res.json;
                } else {
                    this.addressService
                        .find(this.customer.addressId)
                        .subscribe((subRes: Address) => {
                            this.addresses = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.personalInformationService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.personalInfoId) {
                    this.personalinfos = res.json;
                } else {
                    this.personalInformationService
                        .find(this.customer.personalInfoId)
                        .subscribe((subRes: PersonalInformation) => {
                            this.personalinfos = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.avatarService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.avatarId) {
                    this.avatars = res.json;
                } else {
                    this.avatarService
                        .find(this.customer.avatarId)
                        .subscribe((subRes: Avatar) => {
                            this.avatars = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerService.update(this.customer), false);
        } else {
            this.subscribeToSaveResponse(
                this.customerService.create(this.customer), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Customer>, isCreated: boolean) {
        result.subscribe((res: Customer) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Customer, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Customer is created with identifier ${result.id}`
            : `A Customer is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'customerListModification', content: 'OK'});
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

    trackLoginOptionsById(index: number, item: LoginOptions) {
        return item.id;
    }

    trackAddressById(index: number, item: Address) {
        return item.id;
    }

    trackPersonalInformationById(index: number, item: PersonalInformation) {
        return item.id;
    }

    trackAvatarById(index: number, item: Avatar) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-popup',
    template: ''
})
export class CustomerPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPopupService: CustomerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.customerPopupService
                    .open(CustomerDialogComponent, params['id']);
            } else {
                this.modalRef = this.customerPopupService
                    .open(CustomerDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

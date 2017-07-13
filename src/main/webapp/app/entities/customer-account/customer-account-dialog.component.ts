import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CustomerAccount } from './customer-account.model';
import { CustomerAccountPopupService } from './customer-account-popup.service';
import { CustomerAccountService } from './customer-account.service';
import { User, UserService } from '../../shared';
import { Customer, CustomerService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-customer-account-dialog',
    templateUrl: './customer-account-dialog.component.html'
})
export class CustomerAccountDialogComponent implements OnInit {

    customerAccount: CustomerAccount;
    authorities: any[];
    isSaving: boolean;

    users: User[];

    customers: Customer[];
    createdAtDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private customerAccountService: CustomerAccountService,
        private userService: UserService,
        private customerService: CustomerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.customerService
            .query({filter: 'customeraccount-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerAccount.customerId) {
                    this.customers = res.json;
                } else {
                    this.customerService
                        .find(this.customerAccount.customerId)
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
        if (this.customerAccount.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerAccountService.update(this.customerAccount));
        } else {
            this.subscribeToSaveResponse(
                this.customerAccountService.create(this.customerAccount));
        }
    }

    private subscribeToSaveResponse(result: Observable<CustomerAccount>) {
        result.subscribe((res: CustomerAccount) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: CustomerAccount) {
        this.eventManager.broadcast({ name: 'customerAccountListModification', content: 'OK'});
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

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-account-popup',
    template: ''
})
export class CustomerAccountPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerAccountPopupService: CustomerAccountPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.customerAccountPopupService
                    .open(CustomerAccountDialogComponent, params['id']);
            } else {
                this.modalRef = this.customerAccountPopupService
                    .open(CustomerAccountDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

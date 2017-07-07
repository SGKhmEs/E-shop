import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Customer } from './customer.model';
import { CustomerPopupService } from './customer-popup.service';
import { CustomerService } from './customer.service';
import { LoginOptions, LoginOptionsService } from '../login-options';
<<<<<<< HEAD
=======
import { Confirm, ConfirmService } from '../confirm';
>>>>>>> with_entities
import { CustomerRoom, CustomerRoomService } from '../customer-room';
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

<<<<<<< HEAD
    customerrooms: CustomerRoom[];
=======
    confirms: Confirm[];

    userrooms: CustomerRoom[];
>>>>>>> with_entities

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private customerService: CustomerService,
        private loginOptionsService: LoginOptionsService,
<<<<<<< HEAD
=======
        private confirmService: ConfirmService,
>>>>>>> with_entities
        private customerRoomService: CustomerRoomService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.loginOptionsService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
<<<<<<< HEAD
                if (!this.customer.loginOptions || !this.customer.loginOptions.id) {
                    this.loginoptions = res.json;
                } else {
                    this.loginOptionsService
                        .find(this.customer.loginOptions.id)
=======
                if (!this.customer.loginOptionsId) {
                    this.loginoptions = res.json;
                } else {
                    this.loginOptionsService
                        .find(this.customer.loginOptionsId)
>>>>>>> with_entities
                        .subscribe((subRes: LoginOptions) => {
                            this.loginoptions = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
<<<<<<< HEAD
        this.customerRoomService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.customerRoom || !this.customer.customerRoom.id) {
                    this.customerrooms = res.json;
                } else {
                    this.customerRoomService
                        .find(this.customer.customerRoom.id)
                        .subscribe((subRes: CustomerRoom) => {
                            this.customerrooms = [subRes].concat(res.json);
=======
        this.confirmService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.confirmId) {
                    this.confirms = res.json;
                } else {
                    this.confirmService
                        .find(this.customer.confirmId)
                        .subscribe((subRes: Confirm) => {
                            this.confirms = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.customerRoomService
            .query({filter: 'customer-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customer.userRoomId) {
                    this.userrooms = res.json;
                } else {
                    this.customerRoomService
                        .find(this.customer.userRoomId)
                        .subscribe((subRes: CustomerRoom) => {
                            this.userrooms = [subRes].concat(res.json);
>>>>>>> with_entities
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }
<<<<<<< HEAD

=======
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customer.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
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
            isCreated ? 'eshopApp.customer.created'
            : 'eshopApp.customer.updated',
            { param : result.id }, null);

=======
                this.customerService.update(this.customer));
        } else {
            this.subscribeToSaveResponse(
                this.customerService.create(this.customer));
        }
    }

    private subscribeToSaveResponse(result: Observable<Customer>) {
        result.subscribe((res: Customer) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Customer) {
>>>>>>> with_entities
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

<<<<<<< HEAD
=======
    trackConfirmById(index: number, item: Confirm) {
        return item.id;
    }

>>>>>>> with_entities
    trackCustomerRoomById(index: number, item: CustomerRoom) {
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

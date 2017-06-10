import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { CustomerRoom } from './customer-room.model';
import { CustomerRoomPopupService } from './customer-room-popup.service';
import { CustomerRoomService } from './customer-room.service';
import { Address, AddressService } from '../address';
import { PersonalInformation, PersonalInformationService } from '../personal-information';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-customer-room-dialog',
    templateUrl: './customer-room-dialog.component.html'
})
export class CustomerRoomDialogComponent implements OnInit {

    customerRoom: CustomerRoom;
    authorities: any[];
    isSaving: boolean;

    addresses: Address[];

    personalinfos: PersonalInformation[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private customerRoomService: CustomerRoomService,
        private addressService: AddressService,
        private personalInformationService: PersonalInformationService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.addressService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.address || !this.customerRoom.address.id) {
                    this.addresses = res.json;
                } else {
                    this.addressService
                        .find(this.customerRoom.address.id)
                        .subscribe((subRes: Address) => {
                            this.addresses = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.personalInformationService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.personalInfo || !this.customerRoom.personalInfo.id) {
                    this.personalinfos = res.json;
                } else {
                    this.personalInformationService
                        .find(this.customerRoom.personalInfo.id)
                        .subscribe((subRes: PersonalInformation) => {
                            this.personalinfos = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerRoom.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerRoomService.update(this.customerRoom), false);
        } else {
            this.subscribeToSaveResponse(
                this.customerRoomService.create(this.customerRoom), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<CustomerRoom>, isCreated: boolean) {
        result.subscribe((res: CustomerRoom) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: CustomerRoom, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.customerRoom.created'
            : 'eshopApp.customerRoom.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'customerRoomListModification', content: 'OK'});
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

    trackAddressById(index: number, item: Address) {
        return item.id;
    }

    trackPersonalInformationById(index: number, item: PersonalInformation) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-room-popup',
    template: ''
})
export class CustomerRoomPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerRoomPopupService: CustomerRoomPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.customerRoomPopupService
                    .open(CustomerRoomDialogComponent, params['id']);
            } else {
                this.modalRef = this.customerRoomPopupService
                    .open(CustomerRoomDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

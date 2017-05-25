import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { PersonalInformation } from './personal-information.model';
import { PersonalInformationPopupService } from './personal-information-popup.service';
import { PersonalInformationService } from './personal-information.service';
import { Avatar, AvatarService } from '../avatar';
import { AddressShipping, AddressShippingService } from '../address-shipping';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-personal-information-dialog',
    templateUrl: './personal-information-dialog.component.html'
})
export class PersonalInformationDialogComponent implements OnInit {

    personalInformation: PersonalInformation;
    authorities: any[];
    isSaving: boolean;

    avatars: Avatar[];

    addressshippings: AddressShipping[];
    dateBirthDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private personalInformationService: PersonalInformationService,
        private avatarService: AvatarService,
        private addressShippingService: AddressShippingService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.avatarService
            .query({filter: 'personalinformation-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.personalInformation.avatarId) {
                    this.avatars = res.json;
                } else {
                    this.avatarService
                        .find(this.personalInformation.avatarId)
                        .subscribe((subRes: Avatar) => {
                            this.avatars = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.addressShippingService
            .query({filter: 'personalinformation-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.personalInformation.addressShippingId) {
                    this.addressshippings = res.json;
                } else {
                    this.addressShippingService
                        .find(this.personalInformation.addressShippingId)
                        .subscribe((subRes: AddressShipping) => {
                            this.addressshippings = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.personalInformation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.personalInformationService.update(this.personalInformation));
        } else {
            this.subscribeToSaveResponse(
                this.personalInformationService.create(this.personalInformation));
        }
    }

    private subscribeToSaveResponse(result: Observable<PersonalInformation>) {
        result.subscribe((res: PersonalInformation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: PersonalInformation) {
        this.eventManager.broadcast({ name: 'personalInformationListModification', content: 'OK'});
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

    trackAvatarById(index: number, item: Avatar) {
        return item.id;
    }

    trackAddressShippingById(index: number, item: AddressShipping) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-personal-information-popup',
    template: ''
})
export class PersonalInformationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private personalInformationPopupService: PersonalInformationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.personalInformationPopupService
                    .open(PersonalInformationDialogComponent, params['id']);
            } else {
                this.modalRef = this.personalInformationPopupService
                    .open(PersonalInformationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

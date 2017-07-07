import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PersonalInformation } from './personal-information.model';
import { PersonalInformationPopupService } from './personal-information-popup.service';
import { PersonalInformationService } from './personal-information.service';
<<<<<<< HEAD
import { Avatar, AvatarService } from '../avatar';
<<<<<<< HEAD
=======
import { AddressShipping, AddressShippingService } from '../address-shipping';
>>>>>>> with_entities
import { ResponseWrapper } from '../../shared';
=======
>>>>>>> creatingDtos

@Component({
    selector: 'jhi-personal-information-dialog',
    templateUrl: './personal-information-dialog.component.html'
})
export class PersonalInformationDialogComponent implements OnInit {

    personalInformation: PersonalInformation;
    authorities: any[];
    isSaving: boolean;
<<<<<<< HEAD

    avatars: Avatar[];
<<<<<<< HEAD
=======

    addressshippings: AddressShipping[];
>>>>>>> with_entities
=======
>>>>>>> creatingDtos
    dateBirthDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private personalInformationService: PersonalInformationService,
<<<<<<< HEAD
        private avatarService: AvatarService,
<<<<<<< HEAD
=======
        private addressShippingService: AddressShippingService,
>>>>>>> with_entities
        private eventManager: EventManager
=======
        private eventManager: JhiEventManager
>>>>>>> creatingDtos
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
<<<<<<< HEAD
        this.avatarService
            .query({filter: 'personalinformation-is-null'})
            .subscribe((res: ResponseWrapper) => {
<<<<<<< HEAD
                if (!this.personalInformation.avatar || !this.personalInformation.avatar.id) {
                    this.avatars = res.json;
                } else {
                    this.avatarService
                        .find(this.personalInformation.avatar.id)
=======
                if (!this.personalInformation.avatarId) {
                    this.avatars = res.json;
                } else {
                    this.avatarService
                        .find(this.personalInformation.avatarId)
>>>>>>> with_entities
                        .subscribe((subRes: Avatar) => {
                            this.avatars = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
<<<<<<< HEAD
=======
>>>>>>> creatingDtos
    }

=======
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
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.personalInformation.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
                this.personalInformationService.update(this.personalInformation), false);
        } else {
            this.subscribeToSaveResponse(
                this.personalInformationService.create(this.personalInformation), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<PersonalInformation>, isCreated: boolean) {
        result.subscribe((res: PersonalInformation) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: PersonalInformation, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Personal Information is created with identifier ${result.id}`
            : `A Personal Information is updated with identifier ${result.id}`,
            null, null);

=======
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
>>>>>>> with_entities
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
<<<<<<< HEAD

    trackAvatarById(index: number, item: Avatar) {
        return item.id;
    }
<<<<<<< HEAD
=======

    trackAddressShippingById(index: number, item: AddressShipping) {
        return item.id;
    }
>>>>>>> with_entities
=======
>>>>>>> creatingDtos
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

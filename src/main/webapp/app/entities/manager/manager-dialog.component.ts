import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Manager } from './manager.model';
import { ManagerPopupService } from './manager-popup.service';
import { ManagerService } from './manager.service';
import { LoginOptions, LoginOptionsService } from '../login-options';
import { PersonalInformation, PersonalInformationService } from '../personal-information';
import { ManagerAccount, ManagerAccountService } from '../manager-account';
import { Avatar, AvatarService } from '../avatar';
import { Address, AddressService } from '../address';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-manager-dialog',
    templateUrl: './manager-dialog.component.html'
})
export class ManagerDialogComponent implements OnInit {

    manager: Manager;
    authorities: any[];
    isSaving: boolean;

    loginoptions: LoginOptions[];

    personalinfos: PersonalInformation[];

    manageraccounts: ManagerAccount[];

    avatars: Avatar[];

    addresses: Address[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private managerService: ManagerService,
        private loginOptionsService: LoginOptionsService,
        private personalInformationService: PersonalInformationService,
        private managerAccountService: ManagerAccountService,
        private avatarService: AvatarService,
        private addressService: AddressService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.loginOptionsService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.loginOptionsId) {
                    this.loginoptions = res.json;
                } else {
                    this.loginOptionsService
                        .find(this.manager.loginOptionsId)
                        .subscribe((subRes: LoginOptions) => {
                            this.loginoptions = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.personalInformationService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.personalInfoId) {
                    this.personalinfos = res.json;
                } else {
                    this.personalInformationService
                        .find(this.manager.personalInfoId)
                        .subscribe((subRes: PersonalInformation) => {
                            this.personalinfos = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.managerAccountService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.managerAccountId) {
                    this.manageraccounts = res.json;
                } else {
                    this.managerAccountService
                        .find(this.manager.managerAccountId)
                        .subscribe((subRes: ManagerAccount) => {
                            this.manageraccounts = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.avatarService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.avatarId) {
                    this.avatars = res.json;
                } else {
                    this.avatarService
                        .find(this.manager.avatarId)
                        .subscribe((subRes: Avatar) => {
                            this.avatars = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.addressService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.addressId) {
                    this.addresses = res.json;
                } else {
                    this.addressService
                        .find(this.manager.addressId)
                        .subscribe((subRes: Address) => {
                            this.addresses = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.manager.id !== undefined) {
            this.subscribeToSaveResponse(
                this.managerService.update(this.manager));
        } else {
            this.subscribeToSaveResponse(
                this.managerService.create(this.manager));
        }
    }

    private subscribeToSaveResponse(result: Observable<Manager>) {
        result.subscribe((res: Manager) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Manager) {
        this.eventManager.broadcast({ name: 'managerListModification', content: 'OK'});
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

    trackPersonalInformationById(index: number, item: PersonalInformation) {
        return item.id;
    }

    trackManagerAccountById(index: number, item: ManagerAccount) {
        return item.id;
    }

    trackAvatarById(index: number, item: Avatar) {
        return item.id;
    }

    trackAddressById(index: number, item: Address) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-manager-popup',
    template: ''
})
export class ManagerPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private managerPopupService: ManagerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.managerPopupService
                    .open(ManagerDialogComponent, params['id']);
            } else {
                this.modalRef = this.managerPopupService
                    .open(ManagerDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

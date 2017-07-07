import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Manager } from './manager.model';
import { ManagerPopupService } from './manager-popup.service';
import { ManagerService } from './manager.service';
import { LoginOptions, LoginOptionsService } from '../login-options';
import { PersonalInformation, PersonalInformationService } from '../personal-information';
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

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private managerService: ManagerService,
        private loginOptionsService: LoginOptionsService,
        private personalInformationService: PersonalInformationService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.loginOptionsService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.loginOptions || !this.manager.loginOptions.id) {
                    this.loginoptions = res.json;
                } else {
                    this.loginOptionsService
                        .find(this.manager.loginOptions.id)
                        .subscribe((subRes: LoginOptions) => {
                            this.loginoptions = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.personalInformationService
            .query({filter: 'manager-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.manager.personalInfo || !this.manager.personalInfo.id) {
                    this.personalinfos = res.json;
                } else {
                    this.personalInformationService
                        .find(this.manager.personalInfo.id)
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
        if (this.manager.id !== undefined) {
            this.subscribeToSaveResponse(
                this.managerService.update(this.manager), false);
        } else {
            this.subscribeToSaveResponse(
                this.managerService.create(this.manager), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Manager>, isCreated: boolean) {
        result.subscribe((res: Manager) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Manager, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.manager.created'
            : 'eshopApp.manager.updated',
            { param : result.id }, null);

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

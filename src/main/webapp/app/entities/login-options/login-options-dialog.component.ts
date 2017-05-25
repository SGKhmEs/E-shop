import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { LoginOptions } from './login-options.model';
import { LoginOptionsPopupService } from './login-options-popup.service';
import { LoginOptionsService } from './login-options.service';

@Component({
    selector: 'jhi-login-options-dialog',
    templateUrl: './login-options-dialog.component.html'
})
export class LoginOptionsDialogComponent implements OnInit {

    loginOptions: LoginOptions;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private loginOptionsService: LoginOptionsService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.loginOptions.id !== undefined) {
            this.subscribeToSaveResponse(
                this.loginOptionsService.update(this.loginOptions));
        } else {
            this.subscribeToSaveResponse(
                this.loginOptionsService.create(this.loginOptions));
        }
    }

    private subscribeToSaveResponse(result: Observable<LoginOptions>) {
        result.subscribe((res: LoginOptions) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: LoginOptions) {
        this.eventManager.broadcast({ name: 'loginOptionsListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-login-options-popup',
    template: ''
})
export class LoginOptionsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private loginOptionsPopupService: LoginOptionsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.loginOptionsPopupService
                    .open(LoginOptionsDialogComponent, params['id']);
            } else {
                this.modalRef = this.loginOptionsPopupService
                    .open(LoginOptionsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

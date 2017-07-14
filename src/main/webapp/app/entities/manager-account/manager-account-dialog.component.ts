import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ManagerAccount } from './manager-account.model';
import { ManagerAccountPopupService } from './manager-account-popup.service';
import { ManagerAccountService } from './manager-account.service';
import { User, UserService } from '../../shared';
import { Manager, ManagerService } from '../manager';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-manager-account-dialog',
    templateUrl: './manager-account-dialog.component.html'
})
export class ManagerAccountDialogComponent implements OnInit {

    managerAccount: ManagerAccount;
    authorities: any[];
    isSaving: boolean;

    users: User[];

    managers: Manager[];
    createdAtDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private managerAccountService: ManagerAccountService,
        private userService: UserService,
        private managerService: ManagerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.managerService
            .query({filter: 'manageraccount-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.managerAccount.managerId) {
                    this.managers = res.json;
                } else {
                    this.managerService
                        .find(this.managerAccount.managerId)
                        .subscribe((subRes: Manager) => {
                            this.managers = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.managerAccount.id !== undefined) {
            this.subscribeToSaveResponse(
                this.managerAccountService.update(this.managerAccount));
        } else {
            this.subscribeToSaveResponse(
                this.managerAccountService.create(this.managerAccount));
        }
    }

    private subscribeToSaveResponse(result: Observable<ManagerAccount>) {
        result.subscribe((res: ManagerAccount) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ManagerAccount) {
        this.eventManager.broadcast({ name: 'managerAccountListModification', content: 'OK'});
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

    trackManagerById(index: number, item: Manager) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-manager-account-popup',
    template: ''
})
export class ManagerAccountPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private managerAccountPopupService: ManagerAccountPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.managerAccountPopupService
                    .open(ManagerAccountDialogComponent, params['id']);
            } else {
                this.modalRef = this.managerAccountPopupService
                    .open(ManagerAccountDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

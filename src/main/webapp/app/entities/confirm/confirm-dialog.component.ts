import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Confirm } from './confirm.model';
import { ConfirmPopupService } from './confirm-popup.service';
import { ConfirmService } from './confirm.service';

@Component({
    selector: 'jhi-confirm-dialog',
    templateUrl: './confirm-dialog.component.html'
})
export class ConfirmDialogComponent implements OnInit {

    confirm: Confirm;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private confirmService: ConfirmService,
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
        if (this.confirm.id !== undefined) {
            this.subscribeToSaveResponse(
                this.confirmService.update(this.confirm));
        } else {
            this.subscribeToSaveResponse(
                this.confirmService.create(this.confirm));
        }
    }

    private subscribeToSaveResponse(result: Observable<Confirm>) {
        result.subscribe((res: Confirm) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Confirm) {
        this.eventManager.broadcast({ name: 'confirmListModification', content: 'OK'});
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
    selector: 'jhi-confirm-popup',
    template: ''
})
export class ConfirmPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private confirmPopupService: ConfirmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.confirmPopupService
                    .open(ConfirmDialogComponent, params['id']);
            } else {
                this.modalRef = this.confirmPopupService
                    .open(ConfirmDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

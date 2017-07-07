import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { SessionId } from './session-id.model';
import { SessionIdPopupService } from './session-id-popup.service';
import { SessionIdService } from './session-id.service';
import { Customer, CustomerService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-session-id-dialog',
    templateUrl: './session-id-dialog.component.html'
})
export class SessionIdDialogComponent implements OnInit {

    sessionId: SessionId;
    authorities: any[];
    isSaving: boolean;

    customers: Customer[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private sessionIdService: SessionIdService,
        private customerService: CustomerService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sessionId.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sessionIdService.update(this.sessionId));
        } else {
            this.subscribeToSaveResponse(
                this.sessionIdService.create(this.sessionId));
        }
    }

    private subscribeToSaveResponse(result: Observable<SessionId>) {
        result.subscribe((res: SessionId) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: SessionId) {
        this.eventManager.broadcast({ name: 'sessionIdListModification', content: 'OK'});
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

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-session-id-popup',
    template: ''
})
export class SessionIdPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sessionIdPopupService: SessionIdPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.sessionIdPopupService
                    .open(SessionIdDialogComponent, params['id']);
            } else {
                this.modalRef = this.sessionIdPopupService
                    .open(SessionIdDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

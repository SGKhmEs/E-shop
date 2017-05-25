import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Value } from './value.model';
import { ValuePopupService } from './value-popup.service';
import { ValueService } from './value.service';

@Component({
    selector: 'jhi-value-dialog',
    templateUrl: './value-dialog.component.html'
})
export class ValueDialogComponent implements OnInit {

    value: Value;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private valueService: ValueService,
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
        if (this.value.id !== undefined) {
            this.subscribeToSaveResponse(
                this.valueService.update(this.value));
        } else {
            this.subscribeToSaveResponse(
                this.valueService.create(this.value));
        }
    }

    private subscribeToSaveResponse(result: Observable<Value>) {
        result.subscribe((res: Value) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Value) {
        this.eventManager.broadcast({ name: 'valueListModification', content: 'OK'});
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
    selector: 'jhi-value-popup',
    template: ''
})
export class ValuePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private valuePopupService: ValuePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.valuePopupService
                    .open(ValueDialogComponent, params['id']);
            } else {
                this.modalRef = this.valuePopupService
                    .open(ValueDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

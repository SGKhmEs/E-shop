import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Type } from './type.model';
import { TypePopupService } from './type-popup.service';
import { TypeService } from './type.service';

@Component({
    selector: 'jhi-type-dialog',
    templateUrl: './type-dialog.component.html'
})
export class TypeDialogComponent implements OnInit {

    type: Type;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private typeService: TypeService,
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
        if (this.type.id !== undefined) {
            this.subscribeToSaveResponse(
                this.typeService.update(this.type));
        } else {
            this.subscribeToSaveResponse(
                this.typeService.create(this.type));
        }
    }

    private subscribeToSaveResponse(result: Observable<Type>) {
        result.subscribe((res: Type) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Type) {
        this.eventManager.broadcast({ name: 'typeListModification', content: 'OK'});
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
    selector: 'jhi-type-popup',
    template: ''
})
export class TypePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private typePopupService: TypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.typePopupService
                    .open(TypeDialogComponent, params['id']);
            } else {
                this.modalRef = this.typePopupService
                    .open(TypeDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

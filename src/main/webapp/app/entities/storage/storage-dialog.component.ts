import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Storage } from './storage.model';
import { StoragePopupService } from './storage-popup.service';
import { StorageService } from './storage.service';

@Component({
    selector: 'jhi-storage-dialog',
    templateUrl: './storage-dialog.component.html'
})
export class StorageDialogComponent implements OnInit {

    storage: Storage;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private storageService: StorageService,
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
        if (this.storage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.storageService.update(this.storage), false);
        } else {
            this.subscribeToSaveResponse(
                this.storageService.create(this.storage), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Storage>, isCreated: boolean) {
        result.subscribe((res: Storage) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Storage, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.storage.created'
            : 'eshopApp.storage.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'storageListModification', content: 'OK'});
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
    selector: 'jhi-storage-popup',
    template: ''
})
export class StoragePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private storagePopupService: StoragePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.storagePopupService
                    .open(StorageDialogComponent, params['id']);
            } else {
                this.modalRef = this.storagePopupService
                    .open(StorageDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

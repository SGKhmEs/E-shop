import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Bucket } from './bucket.model';
import { BucketPopupService } from './bucket-popup.service';
import { BucketService } from './bucket.service';

@Component({
    selector: 'jhi-bucket-dialog',
    templateUrl: './bucket-dialog.component.html'
})
export class BucketDialogComponent implements OnInit {

    bucket: Bucket;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private bucketService: BucketService,
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
        if (this.bucket.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bucketService.update(this.bucket));
        } else {
            this.subscribeToSaveResponse(
                this.bucketService.create(this.bucket));
        }
    }

    private subscribeToSaveResponse(result: Observable<Bucket>) {
        result.subscribe((res: Bucket) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Bucket) {
        this.eventManager.broadcast({ name: 'bucketListModification', content: 'OK'});
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
    selector: 'jhi-bucket-popup',
    template: ''
})
export class BucketPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bucketPopupService: BucketPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.bucketPopupService
                    .open(BucketDialogComponent, params['id']);
            } else {
                this.modalRef = this.bucketPopupService
                    .open(BucketDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Seen } from './seen.model';
import { SeenPopupService } from './seen-popup.service';
import { SeenService } from './seen.service';

@Component({
    selector: 'jhi-seen-dialog',
    templateUrl: './seen-dialog.component.html'
})
export class SeenDialogComponent implements OnInit {

    seen: Seen;
    authorities: any[];
    isSaving: boolean;
    dataDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private seenService: SeenService,
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
        if (this.seen.id !== undefined) {
            this.subscribeToSaveResponse(
                this.seenService.update(this.seen));
        } else {
            this.subscribeToSaveResponse(
                this.seenService.create(this.seen));
        }
    }

    private subscribeToSaveResponse(result: Observable<Seen>) {
        result.subscribe((res: Seen) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Seen) {
        this.eventManager.broadcast({ name: 'seenListModification', content: 'OK'});
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
    selector: 'jhi-seen-popup',
    template: ''
})
export class SeenPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private seenPopupService: SeenPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.seenPopupService
                    .open(SeenDialogComponent, params['id']);
            } else {
                this.modalRef = this.seenPopupService
                    .open(SeenDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

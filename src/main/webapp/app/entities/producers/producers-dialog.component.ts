import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Producers } from './producers.model';
import { ProducersPopupService } from './producers-popup.service';
import { ProducersService } from './producers.service';

@Component({
    selector: 'jhi-producers-dialog',
    templateUrl: './producers-dialog.component.html'
})
export class ProducersDialogComponent implements OnInit {

    producers: Producers;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private producersService: ProducersService,
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
        if (this.producers.id !== undefined) {
            this.subscribeToSaveResponse(
                this.producersService.update(this.producers));
        } else {
            this.subscribeToSaveResponse(
                this.producersService.create(this.producers));
        }
    }

    private subscribeToSaveResponse(result: Observable<Producers>) {
        result.subscribe((res: Producers) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Producers) {
        this.eventManager.broadcast({ name: 'producersListModification', content: 'OK'});
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
    selector: 'jhi-producers-popup',
    template: ''
})
export class ProducersPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private producersPopupService: ProducersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.producersPopupService
                    .open(ProducersDialogComponent, params['id']);
            } else {
                this.modalRef = this.producersPopupService
                    .open(ProducersDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

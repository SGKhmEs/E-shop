import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Options } from './options.model';
import { OptionsPopupService } from './options-popup.service';
import { OptionsService } from './options.service';

@Component({
    selector: 'jhi-options-dialog',
    templateUrl: './options-dialog.component.html'
})
export class OptionsDialogComponent implements OnInit {

    options: Options;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private optionsService: OptionsService,
        private eventManager: JhiEventManager
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
        if (this.options.id !== undefined) {
            this.subscribeToSaveResponse(
                this.optionsService.update(this.options), false);
        } else {
            this.subscribeToSaveResponse(
                this.optionsService.create(this.options), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Options>, isCreated: boolean) {
        result.subscribe((res: Options) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Options, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Options is created with identifier ${result.id}`
            : `A Options is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'optionsListModification', content: 'OK'});
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
    selector: 'jhi-options-popup',
    template: ''
})
export class OptionsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private optionsPopupService: OptionsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.optionsPopupService
                    .open(OptionsDialogComponent, params['id']);
            } else {
                this.modalRef = this.optionsPopupService
                    .open(OptionsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

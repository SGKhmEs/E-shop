import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StaticPages } from './static-pages.model';
import { StaticPagesPopupService } from './static-pages-popup.service';
import { StaticPagesService } from './static-pages.service';

@Component({
    selector: 'jhi-static-pages-dialog',
    templateUrl: './static-pages-dialog.component.html'
})
export class StaticPagesDialogComponent implements OnInit {

    staticPages: StaticPages;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private staticPagesService: StaticPagesService,
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
        if (this.staticPages.id !== undefined) {
            this.subscribeToSaveResponse(
                this.staticPagesService.update(this.staticPages), false);
        } else {
            this.subscribeToSaveResponse(
                this.staticPagesService.create(this.staticPages), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<StaticPages>, isCreated: boolean) {
        result.subscribe((res: StaticPages) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: StaticPages, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Static Pages is created with identifier ${result.id}`
            : `A Static Pages is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'staticPagesListModification', content: 'OK'});
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
    selector: 'jhi-static-pages-popup',
    template: ''
})
export class StaticPagesPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private staticPagesPopupService: StaticPagesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.staticPagesPopupService
                    .open(StaticPagesDialogComponent, params['id']);
            } else {
                this.modalRef = this.staticPagesPopupService
                    .open(StaticPagesDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

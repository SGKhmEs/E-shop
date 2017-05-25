import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Tags } from './tags.model';
import { TagsPopupService } from './tags-popup.service';
import { TagsService } from './tags.service';

@Component({
    selector: 'jhi-tags-dialog',
    templateUrl: './tags-dialog.component.html'
})
export class TagsDialogComponent implements OnInit {

    tags: Tags;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private tagsService: TagsService,
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
        if (this.tags.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tagsService.update(this.tags));
        } else {
            this.subscribeToSaveResponse(
                this.tagsService.create(this.tags));
        }
    }

    private subscribeToSaveResponse(result: Observable<Tags>) {
        result.subscribe((res: Tags) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Tags) {
        this.eventManager.broadcast({ name: 'tagsListModification', content: 'OK'});
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
    selector: 'jhi-tags-popup',
    template: ''
})
export class TagsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tagsPopupService: TagsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.tagsPopupService
                    .open(TagsDialogComponent, params['id']);
            } else {
                this.modalRef = this.tagsPopupService
                    .open(TagsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

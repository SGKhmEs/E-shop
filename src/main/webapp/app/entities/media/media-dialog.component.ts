import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Media } from './media.model';
import { MediaPopupService } from './media-popup.service';
import { MediaService } from './media.service';
import { Comments, CommentsService } from '../comments';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-media-dialog',
    templateUrl: './media-dialog.component.html'
})
export class MediaDialogComponent implements OnInit {

    media: Media;
    authorities: any[];
    isSaving: boolean;

    comments: Comments[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private mediaService: MediaService,
        private commentsService: CommentsService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.commentsService.query()
            .subscribe((res: ResponseWrapper) => { this.comments = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.media.id !== undefined) {
            this.subscribeToSaveResponse(
                this.mediaService.update(this.media));
        } else {
            this.subscribeToSaveResponse(
                this.mediaService.create(this.media));
        }
    }

    private subscribeToSaveResponse(result: Observable<Media>) {
        result.subscribe((res: Media) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Media) {
        this.eventManager.broadcast({ name: 'mediaListModification', content: 'OK'});
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

    trackCommentsById(index: number, item: Comments) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-media-popup',
    template: ''
})
export class MediaPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mediaPopupService: MediaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.mediaPopupService
                    .open(MediaDialogComponent, params['id']);
            } else {
                this.modalRef = this.mediaPopupService
                    .open(MediaDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

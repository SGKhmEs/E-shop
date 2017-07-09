import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { Comments } from './comments.model';
import { CommentsPopupService } from './comments-popup.service';
import { CommentsService } from './comments.service';

@Component({
    selector: 'jhi-comments-delete-dialog',
    templateUrl: './comments-delete-dialog.component.html'
})
export class CommentsDeleteDialogComponent {

    comments: Comments;

    constructor(
        private commentsService: CommentsService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.commentsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'commentsListModification',
                content: 'Deleted an comments'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success(`A Comments is deleted with identifier ${id}`, null, null);
    }
}

@Component({
    selector: 'jhi-comments-delete-popup',
    template: ''
})
export class CommentsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private commentsPopupService: CommentsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.commentsPopupService
                .open(CommentsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

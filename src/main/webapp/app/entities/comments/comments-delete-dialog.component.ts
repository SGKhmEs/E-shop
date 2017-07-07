import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities
=======
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
>>>>>>> creatingDtos

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
<<<<<<< HEAD
<<<<<<< HEAD
        private alertService: AlertService,
=======
>>>>>>> with_entities
        private eventManager: EventManager
=======
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
>>>>>>> creatingDtos
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
<<<<<<< HEAD
<<<<<<< HEAD
        this.alertService.success('eshopApp.comments.deleted', { param : id }, null);
=======
>>>>>>> with_entities
=======
        this.alertService.success(`A Comments is deleted with identifier ${id}`, null, null);
>>>>>>> creatingDtos
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

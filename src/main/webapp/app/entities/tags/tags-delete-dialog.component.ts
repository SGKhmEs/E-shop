import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Tags } from './tags.model';
import { TagsPopupService } from './tags-popup.service';
import { TagsService } from './tags.service';

@Component({
    selector: 'jhi-tags-delete-dialog',
    templateUrl: './tags-delete-dialog.component.html'
})
export class TagsDeleteDialogComponent {

    tags: Tags;

    constructor(
        private tagsService: TagsService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tagsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tagsListModification',
                content: 'Deleted an tags'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tags-delete-popup',
    template: ''
})
export class TagsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tagsPopupService: TagsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.tagsPopupService
                .open(TagsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

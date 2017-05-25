import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Media } from './media.model';
import { MediaPopupService } from './media-popup.service';
import { MediaService } from './media.service';

@Component({
    selector: 'jhi-media-delete-dialog',
    templateUrl: './media-delete-dialog.component.html'
})
export class MediaDeleteDialogComponent {

    media: Media;

    constructor(
        private mediaService: MediaService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mediaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'mediaListModification',
                content: 'Deleted an media'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-media-delete-popup',
    template: ''
})
export class MediaDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mediaPopupService: MediaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.mediaPopupService
                .open(MediaDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

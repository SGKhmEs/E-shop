import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Seen } from './seen.model';
import { SeenPopupService } from './seen-popup.service';
import { SeenService } from './seen.service';

@Component({
    selector: 'jhi-seen-delete-dialog',
    templateUrl: './seen-delete-dialog.component.html'
})
export class SeenDeleteDialogComponent {

    seen: Seen;

    constructor(
        private seenService: SeenService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.seenService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'seenListModification',
                content: 'Deleted an seen'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-seen-delete-popup',
    template: ''
})
export class SeenDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private seenPopupService: SeenPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.seenPopupService
                .open(SeenDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

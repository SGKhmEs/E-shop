import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { SessionId } from './session-id.model';
import { SessionIdPopupService } from './session-id-popup.service';
import { SessionIdService } from './session-id.service';

@Component({
    selector: 'jhi-session-id-delete-dialog',
    templateUrl: './session-id-delete-dialog.component.html'
})
export class SessionIdDeleteDialogComponent {

    sessionId: SessionId;

    constructor(
        private sessionIdService: SessionIdService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sessionIdService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sessionIdListModification',
                content: 'Deleted an sessionId'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-session-id-delete-popup',
    template: ''
})
export class SessionIdDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sessionIdPopupService: SessionIdPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.sessionIdPopupService
                .open(SessionIdDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

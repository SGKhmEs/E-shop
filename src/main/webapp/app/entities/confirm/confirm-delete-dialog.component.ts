import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Confirm } from './confirm.model';
import { ConfirmPopupService } from './confirm-popup.service';
import { ConfirmService } from './confirm.service';

@Component({
    selector: 'jhi-confirm-delete-dialog',
    templateUrl: './confirm-delete-dialog.component.html'
})
export class ConfirmDeleteDialogComponent {

    confirm: Confirm;

    constructor(
        private confirmService: ConfirmService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.confirmService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'confirmListModification',
                content: 'Deleted an confirm'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-confirm-delete-popup',
    template: ''
})
export class ConfirmDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private confirmPopupService: ConfirmPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.confirmPopupService
                .open(ConfirmDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

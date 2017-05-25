import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Storage } from './storage.model';
import { StoragePopupService } from './storage-popup.service';
import { StorageService } from './storage.service';

@Component({
    selector: 'jhi-storage-delete-dialog',
    templateUrl: './storage-delete-dialog.component.html'
})
export class StorageDeleteDialogComponent {

    storage: Storage;

    constructor(
        private storageService: StorageService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.storageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'storageListModification',
                content: 'Deleted an storage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-storage-delete-popup',
    template: ''
})
export class StorageDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private storagePopupService: StoragePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.storagePopupService
                .open(StorageDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

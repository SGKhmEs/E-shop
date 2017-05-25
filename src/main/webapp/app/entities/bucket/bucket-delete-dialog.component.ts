import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Bucket } from './bucket.model';
import { BucketPopupService } from './bucket-popup.service';
import { BucketService } from './bucket.service';

@Component({
    selector: 'jhi-bucket-delete-dialog',
    templateUrl: './bucket-delete-dialog.component.html'
})
export class BucketDeleteDialogComponent {

    bucket: Bucket;

    constructor(
        private bucketService: BucketService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bucketService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'bucketListModification',
                content: 'Deleted an bucket'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bucket-delete-popup',
    template: ''
})
export class BucketDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bucketPopupService: BucketPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.bucketPopupService
                .open(BucketDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

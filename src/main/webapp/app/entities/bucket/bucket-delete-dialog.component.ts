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
        this.bucketService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'bucketListModification',
                content: 'Deleted an bucket'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
<<<<<<< HEAD
        this.alertService.success('eshopApp.bucket.deleted', { param : id }, null);
=======
>>>>>>> with_entities
=======
        this.alertService.success(`A Bucket is deleted with identifier ${id}`, null, null);
>>>>>>> creatingDtos
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

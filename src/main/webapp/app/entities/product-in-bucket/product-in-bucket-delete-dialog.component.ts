import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { ProductInBucket } from './product-in-bucket.model';
import { ProductInBucketPopupService } from './product-in-bucket-popup.service';
import { ProductInBucketService } from './product-in-bucket.service';

@Component({
    selector: 'jhi-product-in-bucket-delete-dialog',
    templateUrl: './product-in-bucket-delete-dialog.component.html'
})
export class ProductInBucketDeleteDialogComponent {

    productInBucket: ProductInBucket;

    constructor(
        private productInBucketService: ProductInBucketService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.productInBucketService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'productInBucketListModification',
                content: 'Deleted an productInBucket'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success(`A Product In Bucket is deleted with identifier ${id}`, null, null);
    }
}

@Component({
    selector: 'jhi-product-in-bucket-delete-popup',
    template: ''
})
export class ProductInBucketDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private productInBucketPopupService: ProductInBucketPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.productInBucketPopupService
                .open(ProductInBucketDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

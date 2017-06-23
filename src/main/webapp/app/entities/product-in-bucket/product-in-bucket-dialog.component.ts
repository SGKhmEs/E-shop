import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProductInBucket } from './product-in-bucket.model';
import { ProductInBucketPopupService } from './product-in-bucket-popup.service';
import { ProductInBucketService } from './product-in-bucket.service';
import { Bucket, BucketService } from '../bucket';
import { Products, ProductsService } from '../products';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-product-in-bucket-dialog',
    templateUrl: './product-in-bucket-dialog.component.html'
})
export class ProductInBucketDialogComponent implements OnInit {

    productInBucket: ProductInBucket;
    authorities: any[];
    isSaving: boolean;

    buckets: Bucket[];

    products: Products[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private productInBucketService: ProductInBucketService,
        private bucketService: BucketService,
        private productsService: ProductsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.bucketService.query()
            .subscribe((res: ResponseWrapper) => { this.buckets = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.productsService.query()
            .subscribe((res: ResponseWrapper) => { this.products = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.productInBucket.id !== undefined) {
            this.subscribeToSaveResponse(
                this.productInBucketService.update(this.productInBucket), false);
        } else {
            this.subscribeToSaveResponse(
                this.productInBucketService.create(this.productInBucket), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<ProductInBucket>, isCreated: boolean) {
        result.subscribe((res: ProductInBucket) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ProductInBucket, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Product In Bucket is created with identifier ${result.id}`
            : `A Product In Bucket is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'productInBucketListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackBucketById(index: number, item: Bucket) {
        return item.id;
    }

    trackProductsById(index: number, item: Products) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-product-in-bucket-popup',
    template: ''
})
export class ProductInBucketPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private productInBucketPopupService: ProductInBucketPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.productInBucketPopupService
                    .open(ProductInBucketDialogComponent, params['id']);
            } else {
                this.modalRef = this.productInBucketPopupService
                    .open(ProductInBucketDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

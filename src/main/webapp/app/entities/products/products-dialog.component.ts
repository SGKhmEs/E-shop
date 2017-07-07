import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Products } from './products.model';
import { ProductsPopupService } from './products-popup.service';
import { ProductsService } from './products.service';
<<<<<<< HEAD
import { Consignment, ConsignmentService } from '../consignment';
import { SubCategory, SubCategoryService } from '../sub-category';
=======
import { WishList, WishListService } from '../wish-list';
import { Seen, SeenService } from '../seen';
import { Bucket, BucketService } from '../bucket';
import { SubCategory, SubCategoryService } from '../sub-category';
import { Media, MediaService } from '../media';
import { Tags, TagsService } from '../tags';
>>>>>>> with_entities
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-products-dialog',
    templateUrl: './products-dialog.component.html'
})
export class ProductsDialogComponent implements OnInit {

    products: Products;
    authorities: any[];
    isSaving: boolean;

<<<<<<< HEAD
    consignments: Consignment[];

    subcategories: SubCategory[];

=======
    wishlists: WishList[];

    seens: Seen[];

    buckets: Bucket[];

    subcategories: SubCategory[];

    media: Media[];

    tags: Tags[];

>>>>>>> with_entities
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private productsService: ProductsService,
<<<<<<< HEAD
        private consignmentService: ConsignmentService,
        private subCategoryService: SubCategoryService,
=======
        private wishListService: WishListService,
        private seenService: SeenService,
        private bucketService: BucketService,
        private subCategoryService: SubCategoryService,
        private mediaService: MediaService,
        private tagsService: TagsService,
>>>>>>> with_entities
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
<<<<<<< HEAD
        this.consignmentService.query()
            .subscribe((res: ResponseWrapper) => { this.consignments = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.subCategoryService.query()
            .subscribe((res: ResponseWrapper) => { this.subcategories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

=======
        this.wishListService.query()
            .subscribe((res: ResponseWrapper) => { this.wishlists = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.seenService.query()
            .subscribe((res: ResponseWrapper) => { this.seens = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.bucketService.query()
            .subscribe((res: ResponseWrapper) => { this.buckets = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.subCategoryService.query()
            .subscribe((res: ResponseWrapper) => { this.subcategories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.mediaService.query()
            .subscribe((res: ResponseWrapper) => { this.media = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.tagsService.query()
            .subscribe((res: ResponseWrapper) => { this.tags = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.products.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
                this.productsService.update(this.products), false);
        } else {
            this.subscribeToSaveResponse(
                this.productsService.create(this.products), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Products>, isCreated: boolean) {
        result.subscribe((res: Products) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Products, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.products.created'
            : 'eshopApp.products.updated',
            { param : result.id }, null);

=======
                this.productsService.update(this.products));
        } else {
            this.subscribeToSaveResponse(
                this.productsService.create(this.products));
        }
    }

    private subscribeToSaveResponse(result: Observable<Products>) {
        result.subscribe((res: Products) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Products) {
>>>>>>> with_entities
        this.eventManager.broadcast({ name: 'productsListModification', content: 'OK'});
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

<<<<<<< HEAD
    trackConsignmentById(index: number, item: Consignment) {
=======
    trackWishListById(index: number, item: WishList) {
        return item.id;
    }

    trackSeenById(index: number, item: Seen) {
        return item.id;
    }

    trackBucketById(index: number, item: Bucket) {
>>>>>>> with_entities
        return item.id;
    }

    trackSubCategoryById(index: number, item: SubCategory) {
        return item.id;
    }
<<<<<<< HEAD
=======

    trackMediaById(index: number, item: Media) {
        return item.id;
    }

    trackTagsById(index: number, item: Tags) {
        return item.id;
    }
>>>>>>> with_entities
}

@Component({
    selector: 'jhi-products-popup',
    template: ''
})
export class ProductsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private productsPopupService: ProductsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.productsPopupService
                    .open(ProductsDialogComponent, params['id']);
            } else {
                this.modalRef = this.productsPopupService
                    .open(ProductsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

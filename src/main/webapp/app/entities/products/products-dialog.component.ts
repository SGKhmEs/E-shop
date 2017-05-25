import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Products } from './products.model';
import { ProductsPopupService } from './products-popup.service';
import { ProductsService } from './products.service';
import { WishList, WishListService } from '../wish-list';
import { Seen, SeenService } from '../seen';
import { Bucket, BucketService } from '../bucket';
import { SubCategory, SubCategoryService } from '../sub-category';
import { Media, MediaService } from '../media';
import { Tags, TagsService } from '../tags';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-products-dialog',
    templateUrl: './products-dialog.component.html'
})
export class ProductsDialogComponent implements OnInit {

    products: Products;
    authorities: any[];
    isSaving: boolean;

    wishlists: WishList[];

    seens: Seen[];

    buckets: Bucket[];

    subcategories: SubCategory[];

    media: Media[];

    tags: Tags[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private productsService: ProductsService,
        private wishListService: WishListService,
        private seenService: SeenService,
        private bucketService: BucketService,
        private subCategoryService: SubCategoryService,
        private mediaService: MediaService,
        private tagsService: TagsService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
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
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.products.id !== undefined) {
            this.subscribeToSaveResponse(
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

    trackWishListById(index: number, item: WishList) {
        return item.id;
    }

    trackSeenById(index: number, item: Seen) {
        return item.id;
    }

    trackBucketById(index: number, item: Bucket) {
        return item.id;
    }

    trackSubCategoryById(index: number, item: SubCategory) {
        return item.id;
    }

    trackMediaById(index: number, item: Media) {
        return item.id;
    }

    trackTagsById(index: number, item: Tags) {
        return item.id;
    }
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

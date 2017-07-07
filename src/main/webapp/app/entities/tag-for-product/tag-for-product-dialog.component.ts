import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { TagForProduct } from './tag-for-product.model';
import { TagForProductPopupService } from './tag-for-product-popup.service';
import { TagForProductService } from './tag-for-product.service';
import { Products, ProductsService } from '../products';
import { Tags, TagsService } from '../tags';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-tag-for-product-dialog',
    templateUrl: './tag-for-product-dialog.component.html'
})
export class TagForProductDialogComponent implements OnInit {

    tagForProduct: TagForProduct;
    authorities: any[];
    isSaving: boolean;

    products: Products[];

    tags: Tags[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private tagForProductService: TagForProductService,
        private productsService: ProductsService,
        private tagsService: TagsService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.productsService.query()
            .subscribe((res: ResponseWrapper) => { this.products = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.tagsService.query()
            .subscribe((res: ResponseWrapper) => { this.tags = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tagForProduct.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tagForProductService.update(this.tagForProduct), false);
        } else {
            this.subscribeToSaveResponse(
                this.tagForProductService.create(this.tagForProduct), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<TagForProduct>, isCreated: boolean) {
        result.subscribe((res: TagForProduct) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: TagForProduct, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.tagForProduct.created'
            : 'eshopApp.tagForProduct.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'tagForProductListModification', content: 'OK'});
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

    trackProductsById(index: number, item: Products) {
        return item.id;
    }

    trackTagsById(index: number, item: Tags) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-tag-for-product-popup',
    template: ''
})
export class TagForProductPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tagForProductPopupService: TagForProductPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.tagForProductPopupService
                    .open(TagForProductDialogComponent, params['id']);
            } else {
                this.modalRef = this.tagForProductPopupService
                    .open(TagForProductDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

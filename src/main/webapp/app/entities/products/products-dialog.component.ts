import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Products } from './products.model';
import { ProductsPopupService } from './products-popup.service';
import { ProductsService } from './products.service';
import { Options, OptionsService } from '../options';
import { Consignment, ConsignmentService } from '../consignment';
import { SubCategory, SubCategoryService } from '../sub-category';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-products-dialog',
    templateUrl: './products-dialog.component.html'
})
export class ProductsDialogComponent implements OnInit {

    products: Products;
    authorities: any[];
    isSaving: boolean;

    options: Options[];

    consignments: Consignment[];

    subcategories: SubCategory[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private productsService: ProductsService,
        private optionsService: OptionsService,
        private consignmentService: ConsignmentService,
        private subCategoryService: SubCategoryService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.optionsService
            .query({filter: 'products-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.products.options || !this.products.options.id) {
                    this.options = res.json;
                } else {
                    this.optionsService
                        .find(this.products.options.id)
                        .subscribe((subRes: Options) => {
                            this.options = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.consignmentService.query()
            .subscribe((res: ResponseWrapper) => { this.consignments = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.subCategoryService.query()
            .subscribe((res: ResponseWrapper) => { this.subcategories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.products.id !== undefined) {
            this.subscribeToSaveResponse(
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
            isCreated ? `A new Products is created with identifier ${result.id}`
            : `A Products is updated with identifier ${result.id}`,
            null, null);

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

    trackOptionsById(index: number, item: Options) {
        return item.id;
    }

    trackConsignmentById(index: number, item: Consignment) {
        return item.id;
    }

    trackSubCategoryById(index: number, item: SubCategory) {
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

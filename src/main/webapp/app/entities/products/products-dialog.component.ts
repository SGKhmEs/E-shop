import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Products } from './products.model';
import { ProductsPopupService } from './products-popup.service';
import { ProductsService } from './products.service';
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

    consignments: Consignment[];

    subcategories: SubCategory[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private productsService: ProductsService,
        private consignmentService: ConsignmentService,
        private subCategoryService: SubCategoryService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
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
            isCreated ? 'eshopApp.products.created'
            : 'eshopApp.products.updated',
            { param : result.id }, null);

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

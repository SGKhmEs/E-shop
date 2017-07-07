import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WishList } from './wish-list.model';
import { WishListPopupService } from './wish-list-popup.service';
import { WishListService } from './wish-list.service';
<<<<<<< HEAD
import { Customer, CustomerService } from '../customer';
import { Products, ProductsService } from '../products';
import { ResponseWrapper } from '../../shared';
=======
>>>>>>> with_entities

@Component({
    selector: 'jhi-wish-list-dialog',
    templateUrl: './wish-list-dialog.component.html'
})
export class WishListDialogComponent implements OnInit {

    wishList: WishList;
    authorities: any[];
    isSaving: boolean;
<<<<<<< HEAD

    customers: Customer[];

    products: Products[];
<<<<<<< HEAD
=======
>>>>>>> with_entities
    dataDp: any;
=======
    dateDp: any;
>>>>>>> creatingDtos

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private wishListService: WishListService,
<<<<<<< HEAD
        private customerService: CustomerService,
        private productsService: ProductsService,
<<<<<<< HEAD
=======
>>>>>>> with_entities
        private eventManager: EventManager
=======
        private eventManager: JhiEventManager
>>>>>>> creatingDtos
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
<<<<<<< HEAD
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.productsService
            .query({filter: 'wishlist-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.wishList.product || !this.wishList.product.id) {
                    this.products = res.json;
                } else {
                    this.productsService
                        .find(this.wishList.product.id)
                        .subscribe((subRes: Products) => {
                            this.products = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

=======
    }
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.wishList.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
                this.wishListService.update(this.wishList), false);
        } else {
            this.subscribeToSaveResponse(
                this.wishListService.create(this.wishList), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<WishList>, isCreated: boolean) {
        result.subscribe((res: WishList) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: WishList, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Wish List is created with identifier ${result.id}`
            : `A Wish List is updated with identifier ${result.id}`,
            null, null);

=======
                this.wishListService.update(this.wishList));
        } else {
            this.subscribeToSaveResponse(
                this.wishListService.create(this.wishList));
        }
    }

    private subscribeToSaveResponse(result: Observable<WishList>) {
        result.subscribe((res: WishList) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: WishList) {
>>>>>>> with_entities
        this.eventManager.broadcast({ name: 'wishListListModification', content: 'OK'});
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

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }

    trackProductsById(index: number, item: Products) {
        return item.id;
    }
=======
>>>>>>> with_entities
}

@Component({
    selector: 'jhi-wish-list-popup',
    template: ''
})
export class WishListPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListPopupService: WishListPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.wishListPopupService
                    .open(WishListDialogComponent, params['id']);
            } else {
                this.modalRef = this.wishListPopupService
                    .open(WishListDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

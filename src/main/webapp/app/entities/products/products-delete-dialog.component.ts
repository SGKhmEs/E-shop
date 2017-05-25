import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Products } from './products.model';
import { ProductsPopupService } from './products-popup.service';
import { ProductsService } from './products.service';

@Component({
    selector: 'jhi-products-delete-dialog',
    templateUrl: './products-delete-dialog.component.html'
})
export class ProductsDeleteDialogComponent {

    products: Products;

    constructor(
        private productsService: ProductsService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.productsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'productsListModification',
                content: 'Deleted an products'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-products-delete-popup',
    template: ''
})
export class ProductsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private productsPopupService: ProductsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.productsPopupService
                .open(ProductsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

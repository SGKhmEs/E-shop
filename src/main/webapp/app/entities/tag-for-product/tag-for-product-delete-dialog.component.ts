import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { TagForProduct } from './tag-for-product.model';
import { TagForProductPopupService } from './tag-for-product-popup.service';
import { TagForProductService } from './tag-for-product.service';

@Component({
    selector: 'jhi-tag-for-product-delete-dialog',
    templateUrl: './tag-for-product-delete-dialog.component.html'
})
export class TagForProductDeleteDialogComponent {

    tagForProduct: TagForProduct;

    constructor(
        private tagForProductService: TagForProductService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tagForProductService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tagForProductListModification',
                content: 'Deleted an tagForProduct'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success(`A Tag For Product is deleted with identifier ${id}`, null, null);
    }
}

@Component({
    selector: 'jhi-tag-for-product-delete-popup',
    template: ''
})
export class TagForProductDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tagForProductPopupService: TagForProductPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.tagForProductPopupService
                .open(TagForProductDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

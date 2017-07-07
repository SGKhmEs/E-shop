import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities

import { AddressShipping } from './address-shipping.model';
import { AddressShippingPopupService } from './address-shipping-popup.service';
import { AddressShippingService } from './address-shipping.service';

@Component({
    selector: 'jhi-address-shipping-delete-dialog',
    templateUrl: './address-shipping-delete-dialog.component.html'
})
export class AddressShippingDeleteDialogComponent {

    addressShipping: AddressShipping;

    constructor(
        private addressShippingService: AddressShippingService,
        public activeModal: NgbActiveModal,
<<<<<<< HEAD
        private alertService: AlertService,
=======
>>>>>>> with_entities
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.addressShippingService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'addressShippingListModification',
                content: 'Deleted an addressShipping'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
        this.alertService.success('eshopApp.addressShipping.deleted', { param : id }, null);
=======
>>>>>>> with_entities
    }
}

@Component({
    selector: 'jhi-address-shipping-delete-popup',
    template: ''
})
export class AddressShippingDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private addressShippingPopupService: AddressShippingPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.addressShippingPopupService
                .open(AddressShippingDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

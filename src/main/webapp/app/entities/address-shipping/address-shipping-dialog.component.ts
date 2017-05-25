import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { AddressShipping } from './address-shipping.model';
import { AddressShippingPopupService } from './address-shipping-popup.service';
import { AddressShippingService } from './address-shipping.service';

@Component({
    selector: 'jhi-address-shipping-dialog',
    templateUrl: './address-shipping-dialog.component.html'
})
export class AddressShippingDialogComponent implements OnInit {

    addressShipping: AddressShipping;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private addressShippingService: AddressShippingService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.addressShipping.id !== undefined) {
            this.subscribeToSaveResponse(
                this.addressShippingService.update(this.addressShipping));
        } else {
            this.subscribeToSaveResponse(
                this.addressShippingService.create(this.addressShipping));
        }
    }

    private subscribeToSaveResponse(result: Observable<AddressShipping>) {
        result.subscribe((res: AddressShipping) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: AddressShipping) {
        this.eventManager.broadcast({ name: 'addressShippingListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-address-shipping-popup',
    template: ''
})
export class AddressShippingPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private addressShippingPopupService: AddressShippingPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.addressShippingPopupService
                    .open(AddressShippingDialogComponent, params['id']);
            } else {
                this.modalRef = this.addressShippingPopupService
                    .open(AddressShippingDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

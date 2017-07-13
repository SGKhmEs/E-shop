import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AddressShipping } from './address-shipping.model';
import { AddressShippingService } from './address-shipping.service';

@Injectable()
export class AddressShippingPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private addressShippingService: AddressShippingService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.addressShippingService.find(id).subscribe((addressShipping) => {
                this.addressShippingModalRef(component, addressShipping);
            });
        } else {
            return this.addressShippingModalRef(component, new AddressShipping());
        }
    }

    addressShippingModalRef(component: Component, addressShipping: AddressShipping): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.addressShipping = addressShipping;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}

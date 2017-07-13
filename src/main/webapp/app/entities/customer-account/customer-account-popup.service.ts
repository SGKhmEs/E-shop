import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CustomerAccount } from './customer-account.model';
import { CustomerAccountService } from './customer-account.service';

@Injectable()
export class CustomerAccountPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private customerAccountService: CustomerAccountService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.customerAccountService.find(id).subscribe((customerAccount) => {
                if (customerAccount.createdAt) {
                    customerAccount.createdAt = {
                        year: customerAccount.createdAt.getFullYear(),
                        month: customerAccount.createdAt.getMonth() + 1,
                        day: customerAccount.createdAt.getDate()
                    };
                }
                this.customerAccountModalRef(component, customerAccount);
            });
        } else {
            return this.customerAccountModalRef(component, new CustomerAccount());
        }
    }

    customerAccountModalRef(component: Component, customerAccount: CustomerAccount): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.customerAccount = customerAccount;
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

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerAccount } from './customer-account.model';
import { CustomerAccountPopupService } from './customer-account-popup.service';
import { CustomerAccountService } from './customer-account.service';

@Component({
    selector: 'jhi-customer-account-delete-dialog',
    templateUrl: './customer-account-delete-dialog.component.html'
})
export class CustomerAccountDeleteDialogComponent {

    customerAccount: CustomerAccount;

    constructor(
        private customerAccountService: CustomerAccountService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerAccountService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerAccountListModification',
                content: 'Deleted an customerAccount'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-account-delete-popup',
    template: ''
})
export class CustomerAccountDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerAccountPopupService: CustomerAccountPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.customerAccountPopupService
                .open(CustomerAccountDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

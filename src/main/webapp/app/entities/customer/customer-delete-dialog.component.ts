import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities

import { Customer } from './customer.model';
import { CustomerPopupService } from './customer-popup.service';
import { CustomerService } from './customer.service';

@Component({
    selector: 'jhi-customer-delete-dialog',
    templateUrl: './customer-delete-dialog.component.html'
})
export class CustomerDeleteDialogComponent {

    customer: Customer;

    constructor(
        private customerService: CustomerService,
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
        this.customerService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerListModification',
                content: 'Deleted an customer'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
        this.alertService.success('eshopApp.customer.deleted', { param : id }, null);
=======
>>>>>>> with_entities
    }
}

@Component({
    selector: 'jhi-customer-delete-popup',
    template: ''
})
export class CustomerDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPopupService: CustomerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.customerPopupService
                .open(CustomerDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities

import { CustomerRoom } from './customer-room.model';
import { CustomerRoomPopupService } from './customer-room-popup.service';
import { CustomerRoomService } from './customer-room.service';

@Component({
    selector: 'jhi-customer-room-delete-dialog',
    templateUrl: './customer-room-delete-dialog.component.html'
})
export class CustomerRoomDeleteDialogComponent {

    customerRoom: CustomerRoom;

    constructor(
        private customerRoomService: CustomerRoomService,
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
        this.customerRoomService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerRoomListModification',
                content: 'Deleted an customerRoom'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
        this.alertService.success('eshopApp.customerRoom.deleted', { param : id }, null);
=======
>>>>>>> with_entities
    }
}

@Component({
    selector: 'jhi-customer-room-delete-popup',
    template: ''
})
export class CustomerRoomDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerRoomPopupService: CustomerRoomPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.customerRoomPopupService
                .open(CustomerRoomDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

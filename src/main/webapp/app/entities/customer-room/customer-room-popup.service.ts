import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CustomerRoom } from './customer-room.model';
import { CustomerRoomService } from './customer-room.service';
@Injectable()
export class CustomerRoomPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private customerRoomService: CustomerRoomService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.customerRoomService.find(id).subscribe((customerRoom) => {
                this.customerRoomModalRef(component, customerRoom);
            });
        } else {
            return this.customerRoomModalRef(component, new CustomerRoom());
        }
    }

    customerRoomModalRef(component: Component, customerRoom: CustomerRoom): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.customerRoom = customerRoom;
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

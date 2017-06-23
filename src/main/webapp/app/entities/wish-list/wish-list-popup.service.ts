import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { WishList } from './wish-list.model';
import { WishListService } from './wish-list.service';

@Injectable()
export class WishListPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private wishListService: WishListService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.wishListService.find(id).subscribe((wishList) => {
                if (wishList.date) {
                    wishList.date = {
                        year: wishList.date.getFullYear(),
                        month: wishList.date.getMonth() + 1,
                        day: wishList.date.getDate()
                    };
                }
                this.wishListModalRef(component, wishList);
            });
        } else {
            return this.wishListModalRef(component, new WishList());
        }
    }

    wishListModalRef(component: Component, wishList: WishList): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.wishList = wishList;
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

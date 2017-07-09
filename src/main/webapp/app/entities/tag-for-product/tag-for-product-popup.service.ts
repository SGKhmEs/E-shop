import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TagForProduct } from './tag-for-product.model';
import { TagForProductService } from './tag-for-product.service';

@Injectable()
export class TagForProductPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private tagForProductService: TagForProductService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.tagForProductService.find(id).subscribe((tagForProduct) => {
                this.tagForProductModalRef(component, tagForProduct);
            });
        } else {
            return this.tagForProductModalRef(component, new TagForProduct());
        }
    }

    tagForProductModalRef(component: Component, tagForProduct: TagForProduct): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tagForProduct = tagForProduct;
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

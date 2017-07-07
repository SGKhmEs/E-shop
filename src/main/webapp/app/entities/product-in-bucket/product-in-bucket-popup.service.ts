import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ProductInBucket } from './product-in-bucket.model';
import { ProductInBucketService } from './product-in-bucket.service';

@Injectable()
export class ProductInBucketPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private productInBucketService: ProductInBucketService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.productInBucketService.find(id).subscribe((productInBucket) => {
                this.productInBucketModalRef(component, productInBucket);
            });
        } else {
            return this.productInBucketModalRef(component, new ProductInBucket());
        }
    }

    productInBucketModalRef(component: Component, productInBucket: ProductInBucket): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.productInBucket = productInBucket;
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

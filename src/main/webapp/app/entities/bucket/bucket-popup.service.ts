import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Bucket } from './bucket.model';
import { BucketService } from './bucket.service';

@Injectable()
export class BucketPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private bucketService: BucketService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.bucketService.find(id).subscribe((bucket) => {
                bucket.date = this.datePipe
                    .transform(bucket.date, 'yyyy-MM-ddThh:mm');
                this.bucketModalRef(component, bucket);
            });
        } else {
            return this.bucketModalRef(component, new Bucket());
        }
    }

    bucketModalRef(component: Component, bucket: Bucket): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.bucket = bucket;
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

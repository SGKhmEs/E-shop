import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Seen } from './seen.model';
import { SeenService } from './seen.service';
<<<<<<< HEAD

=======
>>>>>>> with_entities
@Injectable()
export class SeenPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private seenService: SeenService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.seenService.find(id).subscribe((seen) => {
                if (seen.data) {
                    seen.data = {
                        year: seen.data.getFullYear(),
                        month: seen.data.getMonth() + 1,
                        day: seen.data.getDate()
                    };
                }
                this.seenModalRef(component, seen);
            });
        } else {
            return this.seenModalRef(component, new Seen());
        }
    }

    seenModalRef(component: Component, seen: Seen): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.seen = seen;
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

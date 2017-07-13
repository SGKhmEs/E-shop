import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ManagerAccount } from './manager-account.model';
import { ManagerAccountService } from './manager-account.service';

@Injectable()
export class ManagerAccountPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private managerAccountService: ManagerAccountService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.managerAccountService.find(id).subscribe((managerAccount) => {
                if (managerAccount.createdAt) {
                    managerAccount.createdAt = {
                        year: managerAccount.createdAt.getFullYear(),
                        month: managerAccount.createdAt.getMonth() + 1,
                        day: managerAccount.createdAt.getDate()
                    };
                }
                this.managerAccountModalRef(component, managerAccount);
            });
        } else {
            return this.managerAccountModalRef(component, new ManagerAccount());
        }
    }

    managerAccountModalRef(component: Component, managerAccount: ManagerAccount): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.managerAccount = managerAccount;
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

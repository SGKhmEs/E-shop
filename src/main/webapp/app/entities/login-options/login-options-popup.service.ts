import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LoginOptions } from './login-options.model';
import { LoginOptionsService } from './login-options.service';

@Injectable()
export class LoginOptionsPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private loginOptionsService: LoginOptionsService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.loginOptionsService.find(id).subscribe((loginOptions) => {
                this.loginOptionsModalRef(component, loginOptions);
            });
        } else {
            return this.loginOptionsModalRef(component, new LoginOptions());
        }
    }

    loginOptionsModalRef(component: Component, loginOptions: LoginOptions): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.loginOptions = loginOptions;
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

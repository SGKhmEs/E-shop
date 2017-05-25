import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SessionId } from './session-id.model';
import { SessionIdService } from './session-id.service';
@Injectable()
export class SessionIdPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private sessionIdService: SessionIdService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.sessionIdService.find(id).subscribe((sessionId) => {
                this.sessionIdModalRef(component, sessionId);
            });
        } else {
            return this.sessionIdModalRef(component, new SessionId());
        }
    }

    sessionIdModalRef(component: Component, sessionId: SessionId): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sessionId = sessionId;
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

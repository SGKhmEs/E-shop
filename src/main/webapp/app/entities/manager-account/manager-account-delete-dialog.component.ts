import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ManagerAccount } from './manager-account.model';
import { ManagerAccountPopupService } from './manager-account-popup.service';
import { ManagerAccountService } from './manager-account.service';

@Component({
    selector: 'jhi-manager-account-delete-dialog',
    templateUrl: './manager-account-delete-dialog.component.html'
})
export class ManagerAccountDeleteDialogComponent {

    managerAccount: ManagerAccount;

    constructor(
        private managerAccountService: ManagerAccountService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.managerAccountService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'managerAccountListModification',
                content: 'Deleted an managerAccount'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-manager-account-delete-popup',
    template: ''
})
export class ManagerAccountDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private managerAccountPopupService: ManagerAccountPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.managerAccountPopupService
                .open(ManagerAccountDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

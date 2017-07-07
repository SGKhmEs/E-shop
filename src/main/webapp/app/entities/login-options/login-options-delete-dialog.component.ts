import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities

import { LoginOptions } from './login-options.model';
import { LoginOptionsPopupService } from './login-options-popup.service';
import { LoginOptionsService } from './login-options.service';

@Component({
    selector: 'jhi-login-options-delete-dialog',
    templateUrl: './login-options-delete-dialog.component.html'
})
export class LoginOptionsDeleteDialogComponent {

    loginOptions: LoginOptions;

    constructor(
        private loginOptionsService: LoginOptionsService,
        public activeModal: NgbActiveModal,
<<<<<<< HEAD
        private alertService: AlertService,
=======
>>>>>>> with_entities
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.loginOptionsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'loginOptionsListModification',
                content: 'Deleted an loginOptions'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
        this.alertService.success('eshopApp.loginOptions.deleted', { param : id }, null);
=======
>>>>>>> with_entities
    }
}

@Component({
    selector: 'jhi-login-options-delete-popup',
    template: ''
})
export class LoginOptionsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private loginOptionsPopupService: LoginOptionsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.loginOptionsPopupService
                .open(LoginOptionsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

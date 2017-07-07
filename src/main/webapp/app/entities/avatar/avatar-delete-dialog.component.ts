import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities
=======
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
>>>>>>> creatingDtos

import { Avatar } from './avatar.model';
import { AvatarPopupService } from './avatar-popup.service';
import { AvatarService } from './avatar.service';

@Component({
    selector: 'jhi-avatar-delete-dialog',
    templateUrl: './avatar-delete-dialog.component.html'
})
export class AvatarDeleteDialogComponent {

    avatar: Avatar;

    constructor(
        private avatarService: AvatarService,
        public activeModal: NgbActiveModal,
<<<<<<< HEAD
<<<<<<< HEAD
        private alertService: AlertService,
=======
>>>>>>> with_entities
        private eventManager: EventManager
=======
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
>>>>>>> creatingDtos
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.avatarService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'avatarListModification',
                content: 'Deleted an avatar'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
<<<<<<< HEAD
        this.alertService.success('eshopApp.avatar.deleted', { param : id }, null);
=======
>>>>>>> with_entities
=======
        this.alertService.success(`A Avatar is deleted with identifier ${id}`, null, null);
>>>>>>> creatingDtos
    }
}

@Component({
    selector: 'jhi-avatar-delete-popup',
    template: ''
})
export class AvatarDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private avatarPopupService: AvatarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.avatarPopupService
                .open(AvatarDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

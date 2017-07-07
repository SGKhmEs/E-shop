<<<<<<< HEAD
import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
=======
import { Component, OnInit, OnDestroy } from '@angular/core';
>>>>>>> with_entities
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Avatar } from './avatar.model';
import { AvatarPopupService } from './avatar-popup.service';
import { AvatarService } from './avatar.service';

@Component({
    selector: 'jhi-avatar-dialog',
    templateUrl: './avatar-dialog.component.html'
})
export class AvatarDialogComponent implements OnInit {

    avatar: Avatar;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private avatarService: AvatarService,
<<<<<<< HEAD
        private elementRef: ElementRef,
<<<<<<< HEAD
=======
>>>>>>> with_entities
        private eventManager: EventManager
=======
        private eventManager: JhiEventManager
>>>>>>> creatingDtos
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
<<<<<<< HEAD

=======
>>>>>>> with_entities
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, avatar, field, isImage) {
<<<<<<< HEAD
        if (event && event.target.files && event.target.files[0]) {
=======
        if (event.target.files && event.target.files[0]) {
>>>>>>> with_entities
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                avatar[field] = base64Data;
                avatar[`${field}ContentType`] = file.type;
            });
        }
    }
<<<<<<< HEAD

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.avatar, this.elementRef, field, fieldContentType, idInput);
    }

=======
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.avatar.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
                this.avatarService.update(this.avatar), false);
        } else {
            this.subscribeToSaveResponse(
                this.avatarService.create(this.avatar), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Avatar>, isCreated: boolean) {
        result.subscribe((res: Avatar) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Avatar, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Avatar is created with identifier ${result.id}`
            : `A Avatar is updated with identifier ${result.id}`,
            null, null);

=======
                this.avatarService.update(this.avatar));
        } else {
            this.subscribeToSaveResponse(
                this.avatarService.create(this.avatar));
        }
    }

    private subscribeToSaveResponse(result: Observable<Avatar>) {
        result.subscribe((res: Avatar) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Avatar) {
>>>>>>> with_entities
        this.eventManager.broadcast({ name: 'avatarListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-avatar-popup',
    template: ''
})
export class AvatarPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private avatarPopupService: AvatarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.avatarPopupService
                    .open(AvatarDialogComponent, params['id']);
            } else {
                this.modalRef = this.avatarPopupService
                    .open(AvatarDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

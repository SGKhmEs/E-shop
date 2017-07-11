import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Avatar } from './avatar.model';
import { AvatarPopupService } from './avatar-popup.service';
import { AvatarService } from './avatar.service';
import { Customer, CustomerService } from '../customer';
import { Manager, ManagerService } from '../manager';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-avatar-dialog',
    templateUrl: './avatar-dialog.component.html'
})
export class AvatarDialogComponent implements OnInit {

    avatar: Avatar;
    authorities: any[];
    isSaving: boolean;

    customers: Customer[];

    managers: Manager[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private avatarService: AvatarService,
        private customerService: CustomerService,
        private managerService: ManagerService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.customerService
            .query({filter: 'avatar-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.avatar.customerId) {
                    this.customers = res.json;
                } else {
                    this.customerService
                        .find(this.avatar.customerId)
                        .subscribe((subRes: Customer) => {
                            this.customers = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.managerService
            .query({filter: 'avatar-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.avatar.managerId) {
                    this.managers = res.json;
                } else {
                    this.managerService
                        .find(this.avatar.managerId)
                        .subscribe((subRes: Manager) => {
                            this.managers = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, avatar, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
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

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.avatar, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.avatar.id !== undefined) {
            this.subscribeToSaveResponse(
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

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }

    trackManagerById(index: number, item: Manager) {
        return item.id;
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

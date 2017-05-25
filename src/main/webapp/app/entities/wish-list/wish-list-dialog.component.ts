import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { WishList } from './wish-list.model';
import { WishListPopupService } from './wish-list-popup.service';
import { WishListService } from './wish-list.service';

@Component({
    selector: 'jhi-wish-list-dialog',
    templateUrl: './wish-list-dialog.component.html'
})
export class WishListDialogComponent implements OnInit {

    wishList: WishList;
    authorities: any[];
    isSaving: boolean;
    dataDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private wishListService: WishListService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.wishList.id !== undefined) {
            this.subscribeToSaveResponse(
                this.wishListService.update(this.wishList));
        } else {
            this.subscribeToSaveResponse(
                this.wishListService.create(this.wishList));
        }
    }

    private subscribeToSaveResponse(result: Observable<WishList>) {
        result.subscribe((res: WishList) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: WishList) {
        this.eventManager.broadcast({ name: 'wishListListModification', content: 'OK'});
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
    selector: 'jhi-wish-list-popup',
    template: ''
})
export class WishListPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListPopupService: WishListPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.wishListPopupService
                    .open(WishListDialogComponent, params['id']);
            } else {
                this.modalRef = this.wishListPopupService
                    .open(WishListDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

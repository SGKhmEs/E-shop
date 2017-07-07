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

import { WishList } from './wish-list.model';
import { WishListPopupService } from './wish-list-popup.service';
import { WishListService } from './wish-list.service';

@Component({
    selector: 'jhi-wish-list-delete-dialog',
    templateUrl: './wish-list-delete-dialog.component.html'
})
export class WishListDeleteDialogComponent {

    wishList: WishList;

    constructor(
        private wishListService: WishListService,
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
        this.wishListService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'wishListListModification',
                content: 'Deleted an wishList'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
<<<<<<< HEAD
        this.alertService.success('eshopApp.wishList.deleted', { param : id }, null);
=======
>>>>>>> with_entities
=======
        this.alertService.success(`A Wish List is deleted with identifier ${id}`, null, null);
>>>>>>> creatingDtos
    }
}

@Component({
    selector: 'jhi-wish-list-delete-popup',
    template: ''
})
export class WishListDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListPopupService: WishListPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.wishListPopupService
                .open(WishListDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

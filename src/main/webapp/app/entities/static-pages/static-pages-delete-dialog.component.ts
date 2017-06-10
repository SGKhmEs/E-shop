import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, EventManager } from 'ng-jhipster';

import { StaticPages } from './static-pages.model';
import { StaticPagesPopupService } from './static-pages-popup.service';
import { StaticPagesService } from './static-pages.service';

@Component({
    selector: 'jhi-static-pages-delete-dialog',
    templateUrl: './static-pages-delete-dialog.component.html'
})
export class StaticPagesDeleteDialogComponent {

    staticPages: StaticPages;

    constructor(
        private staticPagesService: StaticPagesService,
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.staticPagesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'staticPagesListModification',
                content: 'Deleted an staticPages'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('eshopApp.staticPages.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-static-pages-delete-popup',
    template: ''
})
export class StaticPagesDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private staticPagesPopupService: StaticPagesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.staticPagesPopupService
                .open(StaticPagesDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

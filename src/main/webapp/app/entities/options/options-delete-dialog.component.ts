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

import { Options } from './options.model';
import { OptionsPopupService } from './options-popup.service';
import { OptionsService } from './options.service';

@Component({
    selector: 'jhi-options-delete-dialog',
    templateUrl: './options-delete-dialog.component.html'
})
export class OptionsDeleteDialogComponent {

    options: Options;

    constructor(
        private optionsService: OptionsService,
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
        this.optionsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'optionsListModification',
                content: 'Deleted an options'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
<<<<<<< HEAD
        this.alertService.success('eshopApp.options.deleted', { param : id }, null);
=======
>>>>>>> with_entities
=======
        this.alertService.success(`A Options is deleted with identifier ${id}`, null, null);
>>>>>>> creatingDtos
    }
}

@Component({
    selector: 'jhi-options-delete-popup',
    template: ''
})
export class OptionsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private optionsPopupService: OptionsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.optionsPopupService
                .open(OptionsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

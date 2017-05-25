import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

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
        private eventManager: EventManager
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

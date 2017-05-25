import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Value } from './value.model';
import { ValuePopupService } from './value-popup.service';
import { ValueService } from './value.service';

@Component({
    selector: 'jhi-value-delete-dialog',
    templateUrl: './value-delete-dialog.component.html'
})
export class ValueDeleteDialogComponent {

    value: Value;

    constructor(
        private valueService: ValueService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.valueService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'valueListModification',
                content: 'Deleted an value'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-value-delete-popup',
    template: ''
})
export class ValueDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private valuePopupService: ValuePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.valuePopupService
                .open(ValueDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

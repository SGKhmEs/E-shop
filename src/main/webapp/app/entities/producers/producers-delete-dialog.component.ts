import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Producers } from './producers.model';
import { ProducersPopupService } from './producers-popup.service';
import { ProducersService } from './producers.service';

@Component({
    selector: 'jhi-producers-delete-dialog',
    templateUrl: './producers-delete-dialog.component.html'
})
export class ProducersDeleteDialogComponent {

    producers: Producers;

    constructor(
        private producersService: ProducersService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.producersService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'producersListModification',
                content: 'Deleted an producers'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-producers-delete-popup',
    template: ''
})
export class ProducersDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private producersPopupService: ProducersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.producersPopupService
                .open(ProducersDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

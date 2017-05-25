import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Consignment } from './consignment.model';
import { ConsignmentPopupService } from './consignment-popup.service';
import { ConsignmentService } from './consignment.service';

@Component({
    selector: 'jhi-consignment-delete-dialog',
    templateUrl: './consignment-delete-dialog.component.html'
})
export class ConsignmentDeleteDialogComponent {

    consignment: Consignment;

    constructor(
        private consignmentService: ConsignmentService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.consignmentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'consignmentListModification',
                content: 'Deleted an consignment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-consignment-delete-popup',
    template: ''
})
export class ConsignmentDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consignmentPopupService: ConsignmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.consignmentPopupService
                .open(ConsignmentDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

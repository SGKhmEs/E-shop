import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { HistoryOrder } from './history-order.model';
import { HistoryOrderPopupService } from './history-order-popup.service';
import { HistoryOrderService } from './history-order.service';

@Component({
    selector: 'jhi-history-order-delete-dialog',
    templateUrl: './history-order-delete-dialog.component.html'
})
export class HistoryOrderDeleteDialogComponent {

    historyOrder: HistoryOrder;

    constructor(
        private historyOrderService: HistoryOrderService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.historyOrderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'historyOrderListModification',
                content: 'Deleted an historyOrder'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-history-order-delete-popup',
    template: ''
})
export class HistoryOrderDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private historyOrderPopupService: HistoryOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.historyOrderPopupService
                .open(HistoryOrderDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { HistoryOrder } from './history-order.model';
import { HistoryOrderPopupService } from './history-order-popup.service';
import { HistoryOrderService } from './history-order.service';

@Component({
    selector: 'jhi-history-order-dialog',
    templateUrl: './history-order-dialog.component.html'
})
export class HistoryOrderDialogComponent implements OnInit {

    historyOrder: HistoryOrder;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private historyOrderService: HistoryOrderService,
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
        if (this.historyOrder.id !== undefined) {
            this.subscribeToSaveResponse(
                this.historyOrderService.update(this.historyOrder));
        } else {
            this.subscribeToSaveResponse(
                this.historyOrderService.create(this.historyOrder));
        }
    }

    private subscribeToSaveResponse(result: Observable<HistoryOrder>) {
        result.subscribe((res: HistoryOrder) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: HistoryOrder) {
        this.eventManager.broadcast({ name: 'historyOrderListModification', content: 'OK'});
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
    selector: 'jhi-history-order-popup',
    template: ''
})
export class HistoryOrderPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private historyOrderPopupService: HistoryOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.historyOrderPopupService
                    .open(HistoryOrderDialogComponent, params['id']);
            } else {
                this.modalRef = this.historyOrderPopupService
                    .open(HistoryOrderDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

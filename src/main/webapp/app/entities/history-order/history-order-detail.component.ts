import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { HistoryOrder } from './history-order.model';
import { HistoryOrderService } from './history-order.service';

@Component({
    selector: 'jhi-history-order-detail',
    templateUrl: './history-order-detail.component.html'
})
export class HistoryOrderDetailComponent implements OnInit, OnDestroy {

    historyOrder: HistoryOrder;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private historyOrderService: HistoryOrderService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInHistoryOrders();
    }

    load(id) {
        this.historyOrderService.find(id).subscribe((historyOrder) => {
            this.historyOrder = historyOrder;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInHistoryOrders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'historyOrderListModification',
            (response) => this.load(this.historyOrder.id)
        );
    }
}

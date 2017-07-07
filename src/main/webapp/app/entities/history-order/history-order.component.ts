import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { HistoryOrder } from './history-order.model';
import { HistoryOrderService } from './history-order.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-history-order',
    templateUrl: './history-order.component.html'
})
export class HistoryOrderComponent implements OnInit, OnDestroy {
historyOrders: HistoryOrder[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private historyOrderService: HistoryOrderService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.historyOrderService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.historyOrders = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.historyOrderService.query().subscribe(
            (res: ResponseWrapper) => {
                this.historyOrders = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInHistoryOrders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: HistoryOrder) {
        return item.id;
    }
    registerChangeInHistoryOrders() {
        this.eventSubscriber = this.eventManager.subscribe('historyOrderListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

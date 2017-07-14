import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { CustomerAccount } from './customer-account.model';
import { CustomerAccountService } from './customer-account.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-customer-account',
    templateUrl: './customer-account.component.html'
})
export class CustomerAccountComponent implements OnInit, OnDestroy {
customerAccounts: CustomerAccount[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private customerAccountService: CustomerAccountService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.customerAccountService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.customerAccounts = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.customerAccountService.query().subscribe(
            (res: ResponseWrapper) => {
                this.customerAccounts = res.json;
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
        this.registerChangeInCustomerAccounts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerAccount) {
        return item.id;
    }
    registerChangeInCustomerAccounts() {
        this.eventSubscriber = this.eventManager.subscribe('customerAccountListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

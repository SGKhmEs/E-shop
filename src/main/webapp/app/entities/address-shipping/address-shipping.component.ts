import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { AddressShipping } from './address-shipping.model';
import { AddressShippingService } from './address-shipping.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-address-shipping',
    templateUrl: './address-shipping.component.html'
})
export class AddressShippingComponent implements OnInit, OnDestroy {
addressShippings: AddressShipping[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private addressShippingService: AddressShippingService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.addressShippingService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.addressShippings = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.addressShippingService.query().subscribe(
            (res: ResponseWrapper) => {
                this.addressShippings = res.json;
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
        this.registerChangeInAddressShippings();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AddressShipping) {
        return item.id;
    }
    registerChangeInAddressShippings() {
        this.eventSubscriber = this.eventManager.subscribe('addressShippingListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

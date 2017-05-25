import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { CustomerRoom } from './customer-room.model';
import { CustomerRoomService } from './customer-room.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-customer-room',
    templateUrl: './customer-room.component.html'
})
export class CustomerRoomComponent implements OnInit, OnDestroy {
customerRooms: CustomerRoom[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private customerRoomService: CustomerRoomService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.customerRoomService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.customerRooms = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.customerRoomService.query().subscribe(
            (res: ResponseWrapper) => {
                this.customerRooms = res.json;
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
        this.registerChangeInCustomerRooms();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerRoom) {
        return item.id;
    }
    registerChangeInCustomerRooms() {
        this.eventSubscriber = this.eventManager.subscribe('customerRoomListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

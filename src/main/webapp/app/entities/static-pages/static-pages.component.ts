import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { StaticPages } from './static-pages.model';
import { StaticPagesService } from './static-pages.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-static-pages',
    templateUrl: './static-pages.component.html'
})
export class StaticPagesComponent implements OnInit, OnDestroy {
staticPages: StaticPages[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private staticPagesService: StaticPagesService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.staticPagesService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.staticPages = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.staticPagesService.query().subscribe(
            (res: ResponseWrapper) => {
                this.staticPages = res.json;
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
        this.registerChangeInStaticPages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StaticPages) {
        return item.id;
    }
    registerChangeInStaticPages() {
        this.eventSubscriber = this.eventManager.subscribe('staticPagesListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

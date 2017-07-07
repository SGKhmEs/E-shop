import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { Bucket } from './bucket.model';
import { BucketService } from './bucket.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-bucket',
    templateUrl: './bucket.component.html'
})
export class BucketComponent implements OnInit, OnDestroy {
<<<<<<< HEAD

    buckets: Bucket[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;
=======
buckets: Bucket[];
    currentAccount: any;
    eventSubscriber: Subscription;
>>>>>>> with_entities
    currentSearch: string;

    constructor(
        private bucketService: BucketService,
<<<<<<< HEAD
        private alertService: AlertService,
        private eventManager: EventManager,
<<<<<<< HEAD
        private parseLinks: ParseLinks,
=======
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
>>>>>>> creatingDtos
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.buckets = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
=======
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
>>>>>>> with_entities
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.bucketService.search({
                query: this.currentSearch,
<<<<<<< HEAD
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            }).subscribe(
                (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
                (res: ResponseWrapper) => this.onError(res.json)
            );
            return;
        }
        this.bucketService.query({
            page: this.page,
            size: this.itemsPerPage,
            sort: this.sort()
        }).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
=======
                }).subscribe(
                    (res: ResponseWrapper) => this.buckets = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.bucketService.query().subscribe(
            (res: ResponseWrapper) => {
                this.buckets = res.json;
                this.currentSearch = '';
            },
>>>>>>> with_entities
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

<<<<<<< HEAD
    reset() {
        this.page = 0;
        this.buckets = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    clear() {
        this.buckets = [];
        this.links = {
            last: 0
        };
        this.page = 0;
        this.predicate = 'id';
        this.reverse = true;
        this.currentSearch = '';
        this.loadAll();
    }

=======
>>>>>>> with_entities
    search(query) {
        if (!query) {
            return this.clear();
        }
<<<<<<< HEAD
        this.buckets = [];
        this.links = {
            last: 0
        };
        this.page = 0;
        this.predicate = '_score';
        this.reverse = false;
        this.currentSearch = query;
        this.loadAll();
    }
=======
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
>>>>>>> with_entities
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInBuckets();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Bucket) {
        return item.id;
    }
    registerChangeInBuckets() {
<<<<<<< HEAD
        this.eventSubscriber = this.eventManager.subscribe('bucketListModification', (response) => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        for (let i = 0; i < data.length; i++) {
            this.buckets.push(data[i]);
        }
=======
        this.eventSubscriber = this.eventManager.subscribe('bucketListModification', (response) => this.loadAll());
>>>>>>> with_entities
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { ProductInBucket } from './product-in-bucket.model';
import { ProductInBucketService } from './product-in-bucket.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-product-in-bucket',
    templateUrl: './product-in-bucket.component.html'
})
export class ProductInBucketComponent implements OnInit, OnDestroy {
productInBuckets: ProductInBucket[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private productInBucketService: ProductInBucketService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.productInBucketService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.productInBuckets = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.productInBucketService.query().subscribe(
            (res: ResponseWrapper) => {
                this.productInBuckets = res.json;
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
        this.registerChangeInProductInBuckets();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ProductInBucket) {
        return item.id;
    }
    registerChangeInProductInBuckets() {
        this.eventSubscriber = this.eventManager.subscribe('productInBucketListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

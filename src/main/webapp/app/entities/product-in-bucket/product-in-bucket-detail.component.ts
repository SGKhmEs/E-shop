import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ProductInBucket } from './product-in-bucket.model';
import { ProductInBucketService } from './product-in-bucket.service';

@Component({
    selector: 'jhi-product-in-bucket-detail',
    templateUrl: './product-in-bucket-detail.component.html'
})
export class ProductInBucketDetailComponent implements OnInit, OnDestroy {

    productInBucket: ProductInBucket;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private productInBucketService: ProductInBucketService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProductInBuckets();
    }

    load(id) {
        this.productInBucketService.find(id).subscribe((productInBucket) => {
            this.productInBucket = productInBucket;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProductInBuckets() {
        this.eventSubscriber = this.eventManager.subscribe(
            'productInBucketListModification',
            (response) => this.load(this.productInBucket.id)
        );
    }
}

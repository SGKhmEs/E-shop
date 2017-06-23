import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Bucket } from './bucket.model';
import { BucketService } from './bucket.service';

@Component({
    selector: 'jhi-bucket-detail',
    templateUrl: './bucket-detail.component.html'
})
export class BucketDetailComponent implements OnInit, OnDestroy {

    bucket: Bucket;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private bucketService: BucketService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBuckets();
    }

    load(id) {
        this.bucketService.find(id).subscribe((bucket) => {
            this.bucket = bucket;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBuckets() {
        this.eventSubscriber = this.eventManager.subscribe(
            'bucketListModification',
            (response) => this.load(this.bucket.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TagForProduct } from './tag-for-product.model';
import { TagForProductService } from './tag-for-product.service';

@Component({
    selector: 'jhi-tag-for-product-detail',
    templateUrl: './tag-for-product-detail.component.html'
})
export class TagForProductDetailComponent implements OnInit, OnDestroy {

    tagForProduct: TagForProduct;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tagForProductService: TagForProductService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTagForProducts();
    }

    load(id) {
        this.tagForProductService.find(id).subscribe((tagForProduct) => {
            this.tagForProduct = tagForProduct;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTagForProducts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tagForProductListModification',
            (response) => this.load(this.tagForProduct.id)
        );
    }
}

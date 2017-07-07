import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { AddressShipping } from './address-shipping.model';
import { AddressShippingService } from './address-shipping.service';

@Component({
    selector: 'jhi-address-shipping-detail',
    templateUrl: './address-shipping-detail.component.html'
})
export class AddressShippingDetailComponent implements OnInit, OnDestroy {

    addressShipping: AddressShipping;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private addressShippingService: AddressShippingService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAddressShippings();
    }

    load(id) {
        this.addressShippingService.find(id).subscribe((addressShipping) => {
            this.addressShipping = addressShipping;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAddressShippings() {
        this.eventSubscriber = this.eventManager.subscribe(
            'addressShippingListModification',
            (response) => this.load(this.addressShipping.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { CustomerAccount } from './customer-account.model';
import { CustomerAccountService } from './customer-account.service';

@Component({
    selector: 'jhi-customer-account-detail',
    templateUrl: './customer-account-detail.component.html'
})
export class CustomerAccountDetailComponent implements OnInit, OnDestroy {

    customerAccount: CustomerAccount;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerAccountService: CustomerAccountService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerAccounts();
    }

    load(id) {
        this.customerAccountService.find(id).subscribe((customerAccount) => {
            this.customerAccount = customerAccount;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerAccounts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerAccountListModification',
            (response) => this.load(this.customerAccount.id)
        );
    }
}

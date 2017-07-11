import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ManagerAccount } from './manager-account.model';
import { ManagerAccountService } from './manager-account.service';

@Component({
    selector: 'jhi-manager-account-detail',
    templateUrl: './manager-account-detail.component.html'
})
export class ManagerAccountDetailComponent implements OnInit, OnDestroy {

    managerAccount: ManagerAccount;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private managerAccountService: ManagerAccountService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInManagerAccounts();
    }

    load(id) {
        this.managerAccountService.find(id).subscribe((managerAccount) => {
            this.managerAccount = managerAccount;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInManagerAccounts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'managerAccountListModification',
            (response) => this.load(this.managerAccount.id)
        );
    }
}

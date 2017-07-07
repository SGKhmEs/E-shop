import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { Confirm } from './confirm.model';
import { ConfirmService } from './confirm.service';

@Component({
    selector: 'jhi-confirm-detail',
    templateUrl: './confirm-detail.component.html'
})
export class ConfirmDetailComponent implements OnInit, OnDestroy {

    confirm: Confirm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private confirmService: ConfirmService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfirms();
    }

    load(id) {
        this.confirmService.find(id).subscribe((confirm) => {
            this.confirm = confirm;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfirms() {
        this.eventSubscriber = this.eventManager.subscribe(
            'confirmListModification',
            (response) => this.load(this.confirm.id)
        );
    }
}

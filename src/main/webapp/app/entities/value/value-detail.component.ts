import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { Value } from './value.model';
import { ValueService } from './value.service';

@Component({
    selector: 'jhi-value-detail',
    templateUrl: './value-detail.component.html'
})
export class ValueDetailComponent implements OnInit, OnDestroy {

    value: Value;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private valueService: ValueService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInValues();
    }

    load(id) {
        this.valueService.find(id).subscribe((value) => {
            this.value = value;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInValues() {
        this.eventSubscriber = this.eventManager.subscribe(
            'valueListModification',
            (response) => this.load(this.value.id)
        );
    }
}

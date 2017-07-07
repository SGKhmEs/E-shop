import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Options } from './options.model';
import { OptionsService } from './options.service';

@Component({
    selector: 'jhi-options-detail',
    templateUrl: './options-detail.component.html'
})
export class OptionsDetailComponent implements OnInit, OnDestroy {

    options: Options;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private optionsService: OptionsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOptions();
    }

    load(id) {
        this.optionsService.find(id).subscribe((options) => {
            this.options = options;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOptions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'optionsListModification',
            (response) => this.load(this.options.id)
        );
    }
}

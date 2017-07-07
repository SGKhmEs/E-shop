import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Producers } from './producers.model';
import { ProducersService } from './producers.service';

@Component({
    selector: 'jhi-producers-detail',
    templateUrl: './producers-detail.component.html'
})
export class ProducersDetailComponent implements OnInit, OnDestroy {

    producers: Producers;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private producersService: ProducersService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProducers();
    }

    load(id) {
        this.producersService.find(id).subscribe((producers) => {
            this.producers = producers;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProducers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'producersListModification',
            (response) => this.load(this.producers.id)
        );
    }
}

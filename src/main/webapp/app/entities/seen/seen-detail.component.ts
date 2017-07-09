import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Seen } from './seen.model';
import { SeenService } from './seen.service';

@Component({
    selector: 'jhi-seen-detail',
    templateUrl: './seen-detail.component.html'
})
export class SeenDetailComponent implements OnInit, OnDestroy {

    seen: Seen;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private seenService: SeenService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSeens();
    }

    load(id) {
        this.seenService.find(id).subscribe((seen) => {
            this.seen = seen;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSeens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'seenListModification',
            (response) => this.load(this.seen.id)
        );
    }
}

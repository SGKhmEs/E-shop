import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { SessionId } from './session-id.model';
import { SessionIdService } from './session-id.service';

@Component({
    selector: 'jhi-session-id-detail',
    templateUrl: './session-id-detail.component.html'
})
export class SessionIdDetailComponent implements OnInit, OnDestroy {

    sessionId: SessionId;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private sessionIdService: SessionIdService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSessionIds();
    }

    load(id) {
        this.sessionIdService.find(id).subscribe((sessionId) => {
            this.sessionId = sessionId;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSessionIds() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sessionIdListModification',
            (response) => this.load(this.sessionId.id)
        );
    }
}

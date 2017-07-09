import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Comments } from './comments.model';
import { CommentsService } from './comments.service';

@Component({
    selector: 'jhi-comments-detail',
    templateUrl: './comments-detail.component.html'
})
export class CommentsDetailComponent implements OnInit, OnDestroy {

    comments: Comments;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private commentsService: CommentsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInComments();
    }

    load(id) {
        this.commentsService.find(id).subscribe((comments) => {
            this.comments = comments;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInComments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'commentsListModification',
            (response) => this.load(this.comments.id)
        );
    }
}

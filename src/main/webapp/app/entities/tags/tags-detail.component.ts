import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Tags } from './tags.model';
import { TagsService } from './tags.service';

@Component({
    selector: 'jhi-tags-detail',
    templateUrl: './tags-detail.component.html'
})
export class TagsDetailComponent implements OnInit, OnDestroy {

    tags: Tags;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tagsService: TagsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTags();
    }

    load(id) {
        this.tagsService.find(id).subscribe((tags) => {
            this.tags = tags;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTags() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tagsListModification',
            (response) => this.load(this.tags.id)
        );
    }
}

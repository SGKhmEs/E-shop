import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Media } from './media.model';
import { MediaService } from './media.service';

@Component({
    selector: 'jhi-media-detail',
    templateUrl: './media-detail.component.html'
})
export class MediaDetailComponent implements OnInit, OnDestroy {

    media: Media;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private mediaService: MediaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMedia();
    }

    load(id) {
        this.mediaService.find(id).subscribe((media) => {
            this.media = media;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMedia() {
        this.eventSubscriber = this.eventManager.subscribe(
            'mediaListModification',
            (response) => this.load(this.media.id)
        );
    }
}

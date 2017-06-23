import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { StaticPages } from './static-pages.model';
import { StaticPagesService } from './static-pages.service';

@Component({
    selector: 'jhi-static-pages-detail',
    templateUrl: './static-pages-detail.component.html'
})
export class StaticPagesDetailComponent implements OnInit, OnDestroy {

    staticPages: StaticPages;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private staticPagesService: StaticPagesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStaticPages();
    }

    load(id) {
        this.staticPagesService.find(id).subscribe((staticPages) => {
            this.staticPages = staticPages;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStaticPages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'staticPagesListModification',
            (response) => this.load(this.staticPages.id)
        );
    }
}

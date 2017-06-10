import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { Type } from './type.model';
import { TypeService } from './type.service';

@Component({
    selector: 'jhi-type-detail',
    templateUrl: './type-detail.component.html'
})
export class TypeDetailComponent implements OnInit, OnDestroy {

    type: Type;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private typeService: TypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTypes();
    }

    load(id) {
        this.typeService.find(id).subscribe((type) => {
            this.type = type;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'typeListModification',
            (response) => this.load(this.type.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Consignment } from './consignment.model';
import { ConsignmentService } from './consignment.service';

@Component({
    selector: 'jhi-consignment-detail',
    templateUrl: './consignment-detail.component.html'
})
export class ConsignmentDetailComponent implements OnInit, OnDestroy {

    consignment: Consignment;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private consignmentService: ConsignmentService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConsignments();
    }

    load(id) {
        this.consignmentService.find(id).subscribe((consignment) => {
            this.consignment = consignment;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConsignments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'consignmentListModification',
            (response) => this.load(this.consignment.id)
        );
    }
}

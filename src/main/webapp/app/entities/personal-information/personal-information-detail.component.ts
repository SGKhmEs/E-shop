import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { PersonalInformation } from './personal-information.model';
import { PersonalInformationService } from './personal-information.service';

@Component({
    selector: 'jhi-personal-information-detail',
    templateUrl: './personal-information-detail.component.html'
})
export class PersonalInformationDetailComponent implements OnInit, OnDestroy {

    personalInformation: PersonalInformation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private personalInformationService: PersonalInformationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPersonalInformations();
    }

    load(id) {
        this.personalInformationService.find(id).subscribe((personalInformation) => {
            this.personalInformation = personalInformation;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPersonalInformations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'personalInformationListModification',
            (response) => this.load(this.personalInformation.id)
        );
    }
}

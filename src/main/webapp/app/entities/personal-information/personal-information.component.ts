import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { PersonalInformation } from './personal-information.model';
import { PersonalInformationService } from './personal-information.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-personal-information',
    templateUrl: './personal-information.component.html'
})
export class PersonalInformationComponent implements OnInit, OnDestroy {
personalInformations: PersonalInformation[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private personalInformationService: PersonalInformationService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.personalInformationService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.personalInformations = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.personalInformationService.query().subscribe(
            (res: ResponseWrapper) => {
                this.personalInformations = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInPersonalInformations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: PersonalInformation) {
        return item.id;
    }
    registerChangeInPersonalInformations() {
        this.eventSubscriber = this.eventManager.subscribe('personalInformationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

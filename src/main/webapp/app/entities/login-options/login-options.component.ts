import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, JhiLanguageService, AlertService } from 'ng-jhipster';

import { LoginOptions } from './login-options.model';
import { LoginOptionsService } from './login-options.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-login-options',
    templateUrl: './login-options.component.html'
})
export class LoginOptionsComponent implements OnInit, OnDestroy {
loginOptions: LoginOptions[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private loginOptionsService: LoginOptionsService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.loginOptionsService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.loginOptions = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.loginOptionsService.query().subscribe(
            (res: ResponseWrapper) => {
                this.loginOptions = res.json;
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
        this.registerChangeInLoginOptions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LoginOptions) {
        return item.id;
    }
    registerChangeInLoginOptions() {
        this.eventSubscriber = this.eventManager.subscribe('loginOptionsListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

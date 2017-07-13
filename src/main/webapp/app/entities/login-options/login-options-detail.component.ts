import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { LoginOptions } from './login-options.model';
import { LoginOptionsService } from './login-options.service';

@Component({
    selector: 'jhi-login-options-detail',
    templateUrl: './login-options-detail.component.html'
})
export class LoginOptionsDetailComponent implements OnInit, OnDestroy {

    loginOptions: LoginOptions;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private loginOptionsService: LoginOptionsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLoginOptions();
    }

    load(id) {
        this.loginOptionsService.find(id).subscribe((loginOptions) => {
            this.loginOptions = loginOptions;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLoginOptions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'loginOptionsListModification',
            (response) => this.load(this.loginOptions.id)
        );
    }
}

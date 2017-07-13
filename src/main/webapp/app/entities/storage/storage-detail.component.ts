import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Storage } from './storage.model';
import { StorageService } from './storage.service';

@Component({
    selector: 'jhi-storage-detail',
    templateUrl: './storage-detail.component.html'
})
export class StorageDetailComponent implements OnInit, OnDestroy {

    storage: Storage;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private storageService: StorageService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStorages();
    }

    load(id) {
        this.storageService.find(id).subscribe((storage) => {
            this.storage = storage;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStorages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'storageListModification',
            (response) => this.load(this.storage.id)
        );
    }
}

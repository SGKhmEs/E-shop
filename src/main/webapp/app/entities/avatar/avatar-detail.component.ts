import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { Avatar } from './avatar.model';
import { AvatarService } from './avatar.service';

@Component({
    selector: 'jhi-avatar-detail',
    templateUrl: './avatar-detail.component.html'
})
export class AvatarDetailComponent implements OnInit, OnDestroy {

    avatar: Avatar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private avatarService: AvatarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAvatars();
    }

    load(id) {
        this.avatarService.find(id).subscribe((avatar) => {
            this.avatar = avatar;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAvatars() {
        this.eventSubscriber = this.eventManager.subscribe(
            'avatarListModification',
            (response) => this.load(this.avatar.id)
        );
    }
}

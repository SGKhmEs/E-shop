import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  } from 'ng-jhipster';

import { CustomerRoom } from './customer-room.model';
import { CustomerRoomService } from './customer-room.service';

@Component({
    selector: 'jhi-customer-room-detail',
    templateUrl: './customer-room-detail.component.html'
})
export class CustomerRoomDetailComponent implements OnInit, OnDestroy {

    customerRoom: CustomerRoom;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private customerRoomService: CustomerRoomService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerRooms();
    }

    load(id) {
        this.customerRoomService.find(id).subscribe((customerRoom) => {
            this.customerRoom = customerRoom;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerRooms() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerRoomListModification',
            (response) => this.load(this.customerRoom.id)
        );
    }
}

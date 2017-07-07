import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { CustomerRoom } from './customer-room.model';
import { CustomerRoomPopupService } from './customer-room-popup.service';
import { CustomerRoomService } from './customer-room.service';
<<<<<<< HEAD
import { Address, AddressService } from '../address';
import { PersonalInformation, PersonalInformationService } from '../personal-information';
=======
import { PersonalInformation, PersonalInformationService } from '../personal-information';
import { WishList, WishListService } from '../wish-list';
import { Address, AddressService } from '../address';
import { Bucket, BucketService } from '../bucket';
import { Seen, SeenService } from '../seen';
import { HistoryOrder, HistoryOrderService } from '../history-order';
>>>>>>> with_entities
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-customer-room-dialog',
    templateUrl: './customer-room-dialog.component.html'
})
export class CustomerRoomDialogComponent implements OnInit {

    customerRoom: CustomerRoom;
    authorities: any[];
    isSaving: boolean;

<<<<<<< HEAD
    addresses: Address[];

    personalinfos: PersonalInformation[];
=======
    personalinfos: PersonalInformation[];

    wishlists: WishList[];

    addresses: Address[];

    buckets: Bucket[];

    seens: Seen[];

    historyorders: HistoryOrder[];
>>>>>>> with_entities

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private customerRoomService: CustomerRoomService,
<<<<<<< HEAD
        private addressService: AddressService,
        private personalInformationService: PersonalInformationService,
=======
        private personalInformationService: PersonalInformationService,
        private wishListService: WishListService,
        private addressService: AddressService,
        private bucketService: BucketService,
        private seenService: SeenService,
        private historyOrderService: HistoryOrderService,
>>>>>>> with_entities
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
<<<<<<< HEAD
        this.addressService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.address || !this.customerRoom.address.id) {
                    this.addresses = res.json;
                } else {
                    this.addressService
                        .find(this.customerRoom.address.id)
=======
        this.personalInformationService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.personalInfoId) {
                    this.personalinfos = res.json;
                } else {
                    this.personalInformationService
                        .find(this.customerRoom.personalInfoId)
                        .subscribe((subRes: PersonalInformation) => {
                            this.personalinfos = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.wishListService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.wishListId) {
                    this.wishlists = res.json;
                } else {
                    this.wishListService
                        .find(this.customerRoom.wishListId)
                        .subscribe((subRes: WishList) => {
                            this.wishlists = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.addressService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.addressId) {
                    this.addresses = res.json;
                } else {
                    this.addressService
                        .find(this.customerRoom.addressId)
>>>>>>> with_entities
                        .subscribe((subRes: Address) => {
                            this.addresses = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
<<<<<<< HEAD
        this.personalInformationService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.personalInfo || !this.customerRoom.personalInfo.id) {
                    this.personalinfos = res.json;
                } else {
                    this.personalInformationService
                        .find(this.customerRoom.personalInfo.id)
                        .subscribe((subRes: PersonalInformation) => {
                            this.personalinfos = [subRes].concat(res.json);
=======
        this.bucketService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.bucketId) {
                    this.buckets = res.json;
                } else {
                    this.bucketService
                        .find(this.customerRoom.bucketId)
                        .subscribe((subRes: Bucket) => {
                            this.buckets = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.seenService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.seenId) {
                    this.seens = res.json;
                } else {
                    this.seenService
                        .find(this.customerRoom.seenId)
                        .subscribe((subRes: Seen) => {
                            this.seens = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.historyOrderService
            .query({filter: 'customerroom-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.customerRoom.historyOrderId) {
                    this.historyorders = res.json;
                } else {
                    this.historyOrderService
                        .find(this.customerRoom.historyOrderId)
                        .subscribe((subRes: HistoryOrder) => {
                            this.historyorders = [subRes].concat(res.json);
>>>>>>> with_entities
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }
<<<<<<< HEAD

=======
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerRoom.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
                this.customerRoomService.update(this.customerRoom), false);
        } else {
            this.subscribeToSaveResponse(
                this.customerRoomService.create(this.customerRoom), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<CustomerRoom>, isCreated: boolean) {
        result.subscribe((res: CustomerRoom) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: CustomerRoom, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.customerRoom.created'
            : 'eshopApp.customerRoom.updated',
            { param : result.id }, null);

=======
                this.customerRoomService.update(this.customerRoom));
        } else {
            this.subscribeToSaveResponse(
                this.customerRoomService.create(this.customerRoom));
        }
    }

    private subscribeToSaveResponse(result: Observable<CustomerRoom>) {
        result.subscribe((res: CustomerRoom) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: CustomerRoom) {
>>>>>>> with_entities
        this.eventManager.broadcast({ name: 'customerRoomListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

<<<<<<< HEAD
=======
    trackPersonalInformationById(index: number, item: PersonalInformation) {
        return item.id;
    }

    trackWishListById(index: number, item: WishList) {
        return item.id;
    }

>>>>>>> with_entities
    trackAddressById(index: number, item: Address) {
        return item.id;
    }

<<<<<<< HEAD
    trackPersonalInformationById(index: number, item: PersonalInformation) {
=======
    trackBucketById(index: number, item: Bucket) {
        return item.id;
    }

    trackSeenById(index: number, item: Seen) {
        return item.id;
    }

    trackHistoryOrderById(index: number, item: HistoryOrder) {
>>>>>>> with_entities
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-room-popup',
    template: ''
})
export class CustomerRoomPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerRoomPopupService: CustomerRoomPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.customerRoomPopupService
                    .open(CustomerRoomDialogComponent, params['id']);
            } else {
                this.modalRef = this.customerRoomPopupService
                    .open(CustomerRoomDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

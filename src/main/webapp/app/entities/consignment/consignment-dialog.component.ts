import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Consignment } from './consignment.model';
import { ConsignmentPopupService } from './consignment-popup.service';
import { ConsignmentService } from './consignment.service';
import { Storage, StorageService } from '../storage';
import { Producers, ProducersService } from '../producers';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-consignment-dialog',
    templateUrl: './consignment-dialog.component.html'
})
export class ConsignmentDialogComponent implements OnInit {

    consignment: Consignment;
    authorities: any[];
    isSaving: boolean;

    storages: Storage[];

    producers: Producers[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private consignmentService: ConsignmentService,
        private storageService: StorageService,
        private producersService: ProducersService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.storageService.query()
            .subscribe((res: ResponseWrapper) => { this.storages = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.producersService.query()
            .subscribe((res: ResponseWrapper) => { this.producers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.consignment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.consignmentService.update(this.consignment), false);
        } else {
            this.subscribeToSaveResponse(
                this.consignmentService.create(this.consignment), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Consignment>, isCreated: boolean) {
        result.subscribe((res: Consignment) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Consignment, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Consignment is created with identifier ${result.id}`
            : `A Consignment is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'consignmentListModification', content: 'OK'});
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

    trackStorageById(index: number, item: Storage) {
        return item.id;
    }

    trackProducersById(index: number, item: Producers) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-consignment-popup',
    template: ''
})
export class ConsignmentPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consignmentPopupService: ConsignmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.consignmentPopupService
                    .open(ConsignmentDialogComponent, params['id']);
            } else {
                this.modalRef = this.consignmentPopupService
                    .open(ConsignmentDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

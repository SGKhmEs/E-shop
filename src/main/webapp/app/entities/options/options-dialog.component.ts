import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Options } from './options.model';
import { OptionsPopupService } from './options-popup.service';
import { OptionsService } from './options.service';
import { Value, ValueService } from '../value';
import { Type, TypeService } from '../type';
import { SubCategory, SubCategoryService } from '../sub-category';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-options-dialog',
    templateUrl: './options-dialog.component.html'
})
export class OptionsDialogComponent implements OnInit {

    options: Options;
    authorities: any[];
    isSaving: boolean;

    values: Value[];

    types: Type[];

    subcategories: SubCategory[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private optionsService: OptionsService,
        private valueService: ValueService,
        private typeService: TypeService,
        private subCategoryService: SubCategoryService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.valueService
            .query({filter: 'options-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.options.valueId) {
                    this.values = res.json;
                } else {
                    this.valueService
                        .find(this.options.valueId)
                        .subscribe((subRes: Value) => {
                            this.values = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.typeService
            .query({filter: 'options-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.options.typeId) {
                    this.types = res.json;
                } else {
                    this.typeService
                        .find(this.options.typeId)
                        .subscribe((subRes: Type) => {
                            this.types = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.subCategoryService.query()
            .subscribe((res: ResponseWrapper) => { this.subcategories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.options.id !== undefined) {
            this.subscribeToSaveResponse(
                this.optionsService.update(this.options));
        } else {
            this.subscribeToSaveResponse(
                this.optionsService.create(this.options));
        }
    }

    private subscribeToSaveResponse(result: Observable<Options>) {
        result.subscribe((res: Options) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Options) {
        this.eventManager.broadcast({ name: 'optionsListModification', content: 'OK'});
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

    trackValueById(index: number, item: Value) {
        return item.id;
    }

    trackTypeById(index: number, item: Type) {
        return item.id;
    }

    trackSubCategoryById(index: number, item: SubCategory) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-options-popup',
    template: ''
})
export class OptionsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private optionsPopupService: OptionsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.optionsPopupService
                    .open(OptionsDialogComponent, params['id']);
            } else {
                this.modalRef = this.optionsPopupService
                    .open(OptionsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

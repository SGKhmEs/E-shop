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
<<<<<<< HEAD
=======
import { SubCategory, SubCategoryService } from '../sub-category';
>>>>>>> with_entities
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

<<<<<<< HEAD
=======
    subcategories: SubCategory[];

>>>>>>> with_entities
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private optionsService: OptionsService,
        private valueService: ValueService,
        private typeService: TypeService,
<<<<<<< HEAD
=======
        private subCategoryService: SubCategoryService,
>>>>>>> with_entities
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.valueService
            .query({filter: 'options-is-null'})
            .subscribe((res: ResponseWrapper) => {
<<<<<<< HEAD
                if (!this.options.value || !this.options.value.id) {
                    this.values = res.json;
                } else {
                    this.valueService
                        .find(this.options.value.id)
=======
                if (!this.options.valueId) {
                    this.values = res.json;
                } else {
                    this.valueService
                        .find(this.options.valueId)
>>>>>>> with_entities
                        .subscribe((subRes: Value) => {
                            this.values = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.typeService
            .query({filter: 'options-is-null'})
            .subscribe((res: ResponseWrapper) => {
<<<<<<< HEAD
                if (!this.options.type || !this.options.type.id) {
                    this.types = res.json;
                } else {
                    this.typeService
                        .find(this.options.type.id)
=======
                if (!this.options.typeId) {
                    this.types = res.json;
                } else {
                    this.typeService
                        .find(this.options.typeId)
>>>>>>> with_entities
                        .subscribe((subRes: Type) => {
                            this.types = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
<<<<<<< HEAD
    }

=======
        this.subCategoryService.query()
            .subscribe((res: ResponseWrapper) => { this.subcategories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }
>>>>>>> with_entities
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.options.id !== undefined) {
            this.subscribeToSaveResponse(
<<<<<<< HEAD
                this.optionsService.update(this.options), false);
        } else {
            this.subscribeToSaveResponse(
                this.optionsService.create(this.options), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Options>, isCreated: boolean) {
        result.subscribe((res: Options) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Options, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.options.created'
            : 'eshopApp.options.updated',
            { param : result.id }, null);

=======
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
>>>>>>> with_entities
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
<<<<<<< HEAD
=======

    trackSubCategoryById(index: number, item: SubCategory) {
        return item.id;
    }
>>>>>>> with_entities
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

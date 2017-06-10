import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { SubCategory } from './sub-category.model';
import { SubCategoryPopupService } from './sub-category-popup.service';
import { SubCategoryService } from './sub-category.service';
import { Category, CategoryService } from '../category';
import { Options, OptionsService } from '../options';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-sub-category-dialog',
    templateUrl: './sub-category-dialog.component.html'
})
export class SubCategoryDialogComponent implements OnInit {

    subCategory: SubCategory;
    authorities: any[];
    isSaving: boolean;

    categories: Category[];

    options: Options[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private subCategoryService: SubCategoryService,
        private categoryService: CategoryService,
        private optionsService: OptionsService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.categoryService.query()
            .subscribe((res: ResponseWrapper) => { this.categories = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.optionsService
            .query({filter: 'subcategory-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.subCategory.options || !this.subCategory.options.id) {
                    this.options = res.json;
                } else {
                    this.optionsService
                        .find(this.subCategory.options.id)
                        .subscribe((subRes: Options) => {
                            this.options = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.subCategory.id !== undefined) {
            this.subscribeToSaveResponse(
                this.subCategoryService.update(this.subCategory), false);
        } else {
            this.subscribeToSaveResponse(
                this.subCategoryService.create(this.subCategory), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<SubCategory>, isCreated: boolean) {
        result.subscribe((res: SubCategory) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: SubCategory, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'eshopApp.subCategory.created'
            : 'eshopApp.subCategory.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'subCategoryListModification', content: 'OK'});
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

    trackCategoryById(index: number, item: Category) {
        return item.id;
    }

    trackOptionsById(index: number, item: Options) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sub-category-popup',
    template: ''
})
export class SubCategoryPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subCategoryPopupService: SubCategoryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.subCategoryPopupService
                    .open(SubCategoryDialogComponent, params['id']);
            } else {
                this.modalRef = this.subCategoryPopupService
                    .open(SubCategoryDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
<<<<<<< HEAD
<<<<<<< HEAD
import { AlertService, EventManager } from 'ng-jhipster';
=======
import { EventManager } from 'ng-jhipster';
>>>>>>> with_entities
=======
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
>>>>>>> creatingDtos

import { SubCategory } from './sub-category.model';
import { SubCategoryPopupService } from './sub-category-popup.service';
import { SubCategoryService } from './sub-category.service';

@Component({
    selector: 'jhi-sub-category-delete-dialog',
    templateUrl: './sub-category-delete-dialog.component.html'
})
export class SubCategoryDeleteDialogComponent {

    subCategory: SubCategory;

    constructor(
        private subCategoryService: SubCategoryService,
        public activeModal: NgbActiveModal,
<<<<<<< HEAD
<<<<<<< HEAD
        private alertService: AlertService,
=======
>>>>>>> with_entities
        private eventManager: EventManager
=======
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
>>>>>>> creatingDtos
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.subCategoryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'subCategoryListModification',
                content: 'Deleted an subCategory'
            });
            this.activeModal.dismiss(true);
        });
<<<<<<< HEAD
<<<<<<< HEAD
        this.alertService.success('eshopApp.subCategory.deleted', { param : id }, null);
=======
>>>>>>> with_entities
=======
        this.alertService.success(`A Sub Category is deleted with identifier ${id}`, null, null);
>>>>>>> creatingDtos
    }
}

@Component({
    selector: 'jhi-sub-category-delete-popup',
    template: ''
})
export class SubCategoryDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subCategoryPopupService: SubCategoryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.subCategoryPopupService
                .open(SubCategoryDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { PersonalInformation } from './personal-information.model';
import { PersonalInformationPopupService } from './personal-information-popup.service';
import { PersonalInformationService } from './personal-information.service';

@Component({
    selector: 'jhi-personal-information-delete-dialog',
    templateUrl: './personal-information-delete-dialog.component.html'
})
export class PersonalInformationDeleteDialogComponent {

    personalInformation: PersonalInformation;

    constructor(
        private personalInformationService: PersonalInformationService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.personalInformationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'personalInformationListModification',
                content: 'Deleted an personalInformation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-personal-information-delete-popup',
    template: ''
})
export class PersonalInformationDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private personalInformationPopupService: PersonalInformationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.personalInformationPopupService
                .open(PersonalInformationDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

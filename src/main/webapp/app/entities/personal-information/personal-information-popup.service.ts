import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PersonalInformation } from './personal-information.model';
import { PersonalInformationService } from './personal-information.service';

@Injectable()
export class PersonalInformationPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private personalInformationService: PersonalInformationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.personalInformationService.find(id).subscribe((personalInformation) => {
                if (personalInformation.dateBirth) {
                    personalInformation.dateBirth = {
                        year: personalInformation.dateBirth.getFullYear(),
                        month: personalInformation.dateBirth.getMonth() + 1,
                        day: personalInformation.dateBirth.getDate()
                    };
                }
                this.personalInformationModalRef(component, personalInformation);
            });
        } else {
            return this.personalInformationModalRef(component, new PersonalInformation());
        }
    }

    personalInformationModalRef(component: Component, personalInformation: PersonalInformation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.personalInformation = personalInformation;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}

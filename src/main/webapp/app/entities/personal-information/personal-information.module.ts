import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    PersonalInformationService,
    PersonalInformationPopupService,
    PersonalInformationComponent,
    PersonalInformationDetailComponent,
    PersonalInformationDialogComponent,
    PersonalInformationPopupComponent,
    PersonalInformationDeletePopupComponent,
    PersonalInformationDeleteDialogComponent,
    personalInformationRoute,
    personalInformationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...personalInformationRoute,
    ...personalInformationPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PersonalInformationComponent,
        PersonalInformationDetailComponent,
        PersonalInformationDialogComponent,
        PersonalInformationDeleteDialogComponent,
        PersonalInformationPopupComponent,
        PersonalInformationDeletePopupComponent,
    ],
    entryComponents: [
        PersonalInformationComponent,
        PersonalInformationDialogComponent,
        PersonalInformationPopupComponent,
        PersonalInformationDeleteDialogComponent,
        PersonalInformationDeletePopupComponent,
    ],
    providers: [
        PersonalInformationService,
        PersonalInformationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopPersonalInformationModule {}

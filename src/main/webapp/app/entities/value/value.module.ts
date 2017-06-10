import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ValueService,
    ValuePopupService,
    ValueComponent,
    ValueDetailComponent,
    ValueDialogComponent,
    ValuePopupComponent,
    ValueDeletePopupComponent,
    ValueDeleteDialogComponent,
    valueRoute,
    valuePopupRoute,
} from './';

const ENTITY_STATES = [
    ...valueRoute,
    ...valuePopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ValueComponent,
        ValueDetailComponent,
        ValueDialogComponent,
        ValueDeleteDialogComponent,
        ValuePopupComponent,
        ValueDeletePopupComponent,
    ],
    entryComponents: [
        ValueComponent,
        ValueDialogComponent,
        ValuePopupComponent,
        ValueDeleteDialogComponent,
        ValueDeletePopupComponent,
    ],
    providers: [
        ValueService,
        ValuePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopValueModule {}

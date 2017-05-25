import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ConfirmService,
    ConfirmPopupService,
    ConfirmComponent,
    ConfirmDetailComponent,
    ConfirmDialogComponent,
    ConfirmPopupComponent,
    ConfirmDeletePopupComponent,
    ConfirmDeleteDialogComponent,
    confirmRoute,
    confirmPopupRoute,
} from './';

const ENTITY_STATES = [
    ...confirmRoute,
    ...confirmPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConfirmComponent,
        ConfirmDetailComponent,
        ConfirmDialogComponent,
        ConfirmDeleteDialogComponent,
        ConfirmPopupComponent,
        ConfirmDeletePopupComponent,
    ],
    entryComponents: [
        ConfirmComponent,
        ConfirmDialogComponent,
        ConfirmPopupComponent,
        ConfirmDeleteDialogComponent,
        ConfirmDeletePopupComponent,
    ],
    providers: [
        ConfirmService,
        ConfirmPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopConfirmModule {}

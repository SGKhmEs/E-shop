import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    SeenService,
    SeenPopupService,
    SeenComponent,
    SeenDetailComponent,
    SeenDialogComponent,
    SeenPopupComponent,
    SeenDeletePopupComponent,
    SeenDeleteDialogComponent,
    seenRoute,
    seenPopupRoute,
} from './';

const ENTITY_STATES = [
    ...seenRoute,
    ...seenPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SeenComponent,
        SeenDetailComponent,
        SeenDialogComponent,
        SeenDeleteDialogComponent,
        SeenPopupComponent,
        SeenDeletePopupComponent,
    ],
    entryComponents: [
        SeenComponent,
        SeenDialogComponent,
        SeenPopupComponent,
        SeenDeleteDialogComponent,
        SeenDeletePopupComponent,
    ],
    providers: [
        SeenService,
        SeenPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopSeenModule {}

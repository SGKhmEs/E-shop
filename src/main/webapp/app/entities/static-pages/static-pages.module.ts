import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    StaticPagesService,
    StaticPagesPopupService,
    StaticPagesComponent,
    StaticPagesDetailComponent,
    StaticPagesDialogComponent,
    StaticPagesPopupComponent,
    StaticPagesDeletePopupComponent,
    StaticPagesDeleteDialogComponent,
    staticPagesRoute,
    staticPagesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...staticPagesRoute,
    ...staticPagesPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        StaticPagesComponent,
        StaticPagesDetailComponent,
        StaticPagesDialogComponent,
        StaticPagesDeleteDialogComponent,
        StaticPagesPopupComponent,
        StaticPagesDeletePopupComponent,
    ],
    entryComponents: [
        StaticPagesComponent,
        StaticPagesDialogComponent,
        StaticPagesPopupComponent,
        StaticPagesDeleteDialogComponent,
        StaticPagesDeletePopupComponent,
    ],
    providers: [
        StaticPagesService,
        StaticPagesPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopStaticPagesModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    SessionIdService,
    SessionIdPopupService,
    SessionIdComponent,
    SessionIdDetailComponent,
    SessionIdDialogComponent,
    SessionIdPopupComponent,
    SessionIdDeletePopupComponent,
    SessionIdDeleteDialogComponent,
    sessionIdRoute,
    sessionIdPopupRoute,
} from './';

const ENTITY_STATES = [
    ...sessionIdRoute,
    ...sessionIdPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SessionIdComponent,
        SessionIdDetailComponent,
        SessionIdDialogComponent,
        SessionIdDeleteDialogComponent,
        SessionIdPopupComponent,
        SessionIdDeletePopupComponent,
    ],
    entryComponents: [
        SessionIdComponent,
        SessionIdDialogComponent,
        SessionIdPopupComponent,
        SessionIdDeleteDialogComponent,
        SessionIdDeletePopupComponent,
    ],
    providers: [
        SessionIdService,
        SessionIdPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopSessionIdModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    LoginOptionsService,
    LoginOptionsPopupService,
    LoginOptionsComponent,
    LoginOptionsDetailComponent,
    LoginOptionsDialogComponent,
    LoginOptionsPopupComponent,
    LoginOptionsDeletePopupComponent,
    LoginOptionsDeleteDialogComponent,
    loginOptionsRoute,
    loginOptionsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...loginOptionsRoute,
    ...loginOptionsPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        LoginOptionsComponent,
        LoginOptionsDetailComponent,
        LoginOptionsDialogComponent,
        LoginOptionsDeleteDialogComponent,
        LoginOptionsPopupComponent,
        LoginOptionsDeletePopupComponent,
    ],
    entryComponents: [
        LoginOptionsComponent,
        LoginOptionsDialogComponent,
        LoginOptionsPopupComponent,
        LoginOptionsDeleteDialogComponent,
        LoginOptionsDeletePopupComponent,
    ],
    providers: [
        LoginOptionsService,
        LoginOptionsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopLoginOptionsModule {}

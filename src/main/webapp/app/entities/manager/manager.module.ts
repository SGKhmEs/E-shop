import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ManagerService,
    ManagerPopupService,
    ManagerComponent,
    ManagerDetailComponent,
    ManagerDialogComponent,
    ManagerPopupComponent,
    ManagerDeletePopupComponent,
    ManagerDeleteDialogComponent,
    managerRoute,
    managerPopupRoute,
} from './';

const ENTITY_STATES = [
    ...managerRoute,
    ...managerPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ManagerComponent,
        ManagerDetailComponent,
        ManagerDialogComponent,
        ManagerDeleteDialogComponent,
        ManagerPopupComponent,
        ManagerDeletePopupComponent,
    ],
    entryComponents: [
        ManagerComponent,
        ManagerDialogComponent,
        ManagerPopupComponent,
        ManagerDeleteDialogComponent,
        ManagerDeletePopupComponent,
    ],
    providers: [
        ManagerService,
        ManagerPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopManagerModule {}

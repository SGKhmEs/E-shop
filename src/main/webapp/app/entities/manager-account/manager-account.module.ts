import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import { EshopAdminModule } from '../../admin/admin.module';
import {
    ManagerAccountService,
    ManagerAccountPopupService,
    ManagerAccountComponent,
    ManagerAccountDetailComponent,
    ManagerAccountDialogComponent,
    ManagerAccountPopupComponent,
    ManagerAccountDeletePopupComponent,
    ManagerAccountDeleteDialogComponent,
    managerAccountRoute,
    managerAccountPopupRoute,
} from './';

const ENTITY_STATES = [
    ...managerAccountRoute,
    ...managerAccountPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        EshopAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ManagerAccountComponent,
        ManagerAccountDetailComponent,
        ManagerAccountDialogComponent,
        ManagerAccountDeleteDialogComponent,
        ManagerAccountPopupComponent,
        ManagerAccountDeletePopupComponent,
    ],
    entryComponents: [
        ManagerAccountComponent,
        ManagerAccountDialogComponent,
        ManagerAccountPopupComponent,
        ManagerAccountDeleteDialogComponent,
        ManagerAccountDeletePopupComponent,
    ],
    providers: [
        ManagerAccountService,
        ManagerAccountPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopManagerAccountModule {}

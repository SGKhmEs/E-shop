import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import { EshopAdminModule } from '../../admin/admin.module';
import {
    CustomerAccountService,
    CustomerAccountPopupService,
    CustomerAccountComponent,
    CustomerAccountDetailComponent,
    CustomerAccountDialogComponent,
    CustomerAccountPopupComponent,
    CustomerAccountDeletePopupComponent,
    CustomerAccountDeleteDialogComponent,
    customerAccountRoute,
    customerAccountPopupRoute,
} from './';

const ENTITY_STATES = [
    ...customerAccountRoute,
    ...customerAccountPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        EshopAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CustomerAccountComponent,
        CustomerAccountDetailComponent,
        CustomerAccountDialogComponent,
        CustomerAccountDeleteDialogComponent,
        CustomerAccountPopupComponent,
        CustomerAccountDeletePopupComponent,
    ],
    entryComponents: [
        CustomerAccountComponent,
        CustomerAccountDialogComponent,
        CustomerAccountPopupComponent,
        CustomerAccountDeleteDialogComponent,
        CustomerAccountDeletePopupComponent,
    ],
    providers: [
        CustomerAccountService,
        CustomerAccountPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopCustomerAccountModule {}

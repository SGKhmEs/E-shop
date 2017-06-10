import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    CustomerRoomService,
    CustomerRoomPopupService,
    CustomerRoomComponent,
    CustomerRoomDetailComponent,
    CustomerRoomDialogComponent,
    CustomerRoomPopupComponent,
    CustomerRoomDeletePopupComponent,
    CustomerRoomDeleteDialogComponent,
    customerRoomRoute,
    customerRoomPopupRoute,
} from './';

const ENTITY_STATES = [
    ...customerRoomRoute,
    ...customerRoomPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CustomerRoomComponent,
        CustomerRoomDetailComponent,
        CustomerRoomDialogComponent,
        CustomerRoomDeleteDialogComponent,
        CustomerRoomPopupComponent,
        CustomerRoomDeletePopupComponent,
    ],
    entryComponents: [
        CustomerRoomComponent,
        CustomerRoomDialogComponent,
        CustomerRoomPopupComponent,
        CustomerRoomDeleteDialogComponent,
        CustomerRoomDeletePopupComponent,
    ],
    providers: [
        CustomerRoomService,
        CustomerRoomPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopCustomerRoomModule {}

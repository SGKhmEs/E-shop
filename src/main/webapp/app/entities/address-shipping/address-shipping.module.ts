import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    AddressShippingService,
    AddressShippingPopupService,
    AddressShippingComponent,
    AddressShippingDetailComponent,
    AddressShippingDialogComponent,
    AddressShippingPopupComponent,
    AddressShippingDeletePopupComponent,
    AddressShippingDeleteDialogComponent,
    addressShippingRoute,
    addressShippingPopupRoute,
} from './';

const ENTITY_STATES = [
    ...addressShippingRoute,
    ...addressShippingPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AddressShippingComponent,
        AddressShippingDetailComponent,
        AddressShippingDialogComponent,
        AddressShippingDeleteDialogComponent,
        AddressShippingPopupComponent,
        AddressShippingDeletePopupComponent,
    ],
    entryComponents: [
        AddressShippingComponent,
        AddressShippingDialogComponent,
        AddressShippingPopupComponent,
        AddressShippingDeleteDialogComponent,
        AddressShippingDeletePopupComponent,
    ],
    providers: [
        AddressShippingService,
        AddressShippingPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopAddressShippingModule {}

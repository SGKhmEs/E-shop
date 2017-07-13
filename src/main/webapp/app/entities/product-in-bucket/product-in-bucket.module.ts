import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ProductInBucketService,
    ProductInBucketPopupService,
    ProductInBucketComponent,
    ProductInBucketDetailComponent,
    ProductInBucketDialogComponent,
    ProductInBucketPopupComponent,
    ProductInBucketDeletePopupComponent,
    ProductInBucketDeleteDialogComponent,
    productInBucketRoute,
    productInBucketPopupRoute,
} from './';

const ENTITY_STATES = [
    ...productInBucketRoute,
    ...productInBucketPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProductInBucketComponent,
        ProductInBucketDetailComponent,
        ProductInBucketDialogComponent,
        ProductInBucketDeleteDialogComponent,
        ProductInBucketPopupComponent,
        ProductInBucketDeletePopupComponent,
    ],
    entryComponents: [
        ProductInBucketComponent,
        ProductInBucketDialogComponent,
        ProductInBucketPopupComponent,
        ProductInBucketDeleteDialogComponent,
        ProductInBucketDeletePopupComponent,
    ],
    providers: [
        ProductInBucketService,
        ProductInBucketPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopProductInBucketModule {}

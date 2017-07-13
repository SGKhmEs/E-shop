import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    BucketService,
    BucketPopupService,
    BucketComponent,
    BucketDetailComponent,
    BucketDialogComponent,
    BucketPopupComponent,
    BucketDeletePopupComponent,
    BucketDeleteDialogComponent,
    bucketRoute,
    bucketPopupRoute,
} from './';

const ENTITY_STATES = [
    ...bucketRoute,
    ...bucketPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        BucketComponent,
        BucketDetailComponent,
        BucketDialogComponent,
        BucketDeleteDialogComponent,
        BucketPopupComponent,
        BucketDeletePopupComponent,
    ],
    entryComponents: [
        BucketComponent,
        BucketDialogComponent,
        BucketPopupComponent,
        BucketDeleteDialogComponent,
        BucketDeletePopupComponent,
    ],
    providers: [
        BucketService,
        BucketPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopBucketModule {}

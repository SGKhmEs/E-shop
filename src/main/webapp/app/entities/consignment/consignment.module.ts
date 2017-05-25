import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ConsignmentService,
    ConsignmentPopupService,
    ConsignmentComponent,
    ConsignmentDetailComponent,
    ConsignmentDialogComponent,
    ConsignmentPopupComponent,
    ConsignmentDeletePopupComponent,
    ConsignmentDeleteDialogComponent,
    consignmentRoute,
    consignmentPopupRoute,
    ConsignmentResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...consignmentRoute,
    ...consignmentPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConsignmentComponent,
        ConsignmentDetailComponent,
        ConsignmentDialogComponent,
        ConsignmentDeleteDialogComponent,
        ConsignmentPopupComponent,
        ConsignmentDeletePopupComponent,
    ],
    entryComponents: [
        ConsignmentComponent,
        ConsignmentDialogComponent,
        ConsignmentPopupComponent,
        ConsignmentDeleteDialogComponent,
        ConsignmentDeletePopupComponent,
    ],
    providers: [
        ConsignmentService,
        ConsignmentPopupService,
        ConsignmentResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopConsignmentModule {}

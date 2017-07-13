import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    TagForProductService,
    TagForProductPopupService,
    TagForProductComponent,
    TagForProductDetailComponent,
    TagForProductDialogComponent,
    TagForProductPopupComponent,
    TagForProductDeletePopupComponent,
    TagForProductDeleteDialogComponent,
    tagForProductRoute,
    tagForProductPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tagForProductRoute,
    ...tagForProductPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TagForProductComponent,
        TagForProductDetailComponent,
        TagForProductDialogComponent,
        TagForProductDeleteDialogComponent,
        TagForProductPopupComponent,
        TagForProductDeletePopupComponent,
    ],
    entryComponents: [
        TagForProductComponent,
        TagForProductDialogComponent,
        TagForProductPopupComponent,
        TagForProductDeleteDialogComponent,
        TagForProductDeletePopupComponent,
    ],
    providers: [
        TagForProductService,
        TagForProductPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopTagForProductModule {}

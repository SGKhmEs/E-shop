import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ProductsService,
    ProductsPopupService,
    ProductsComponent,
    ProductsDetailComponent,
    ProductsDialogComponent,
    ProductsPopupComponent,
    ProductsDeletePopupComponent,
    ProductsDeleteDialogComponent,
    productsRoute,
    productsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...productsRoute,
    ...productsPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProductsComponent,
        ProductsDetailComponent,
        ProductsDialogComponent,
        ProductsDeleteDialogComponent,
        ProductsPopupComponent,
        ProductsDeletePopupComponent,
    ],
    entryComponents: [
        ProductsComponent,
        ProductsDialogComponent,
        ProductsPopupComponent,
        ProductsDeleteDialogComponent,
        ProductsDeletePopupComponent,
    ],
    providers: [
        ProductsService,
        ProductsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopProductsModule {}

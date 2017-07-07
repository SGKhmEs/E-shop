import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    HistoryOrderService,
    HistoryOrderPopupService,
    HistoryOrderComponent,
    HistoryOrderDetailComponent,
    HistoryOrderDialogComponent,
    HistoryOrderPopupComponent,
    HistoryOrderDeletePopupComponent,
    HistoryOrderDeleteDialogComponent,
    historyOrderRoute,
    historyOrderPopupRoute,
} from './';

const ENTITY_STATES = [
    ...historyOrderRoute,
    ...historyOrderPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        HistoryOrderComponent,
        HistoryOrderDetailComponent,
        HistoryOrderDialogComponent,
        HistoryOrderDeleteDialogComponent,
        HistoryOrderPopupComponent,
        HistoryOrderDeletePopupComponent,
    ],
    entryComponents: [
        HistoryOrderComponent,
        HistoryOrderDialogComponent,
        HistoryOrderPopupComponent,
        HistoryOrderDeleteDialogComponent,
        HistoryOrderDeletePopupComponent,
    ],
    providers: [
        HistoryOrderService,
        HistoryOrderPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopHistoryOrderModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    ProducersService,
    ProducersPopupService,
    ProducersComponent,
    ProducersDetailComponent,
    ProducersDialogComponent,
    ProducersPopupComponent,
    ProducersDeletePopupComponent,
    ProducersDeleteDialogComponent,
    producersRoute,
    producersPopupRoute,
} from './';

const ENTITY_STATES = [
    ...producersRoute,
    ...producersPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProducersComponent,
        ProducersDetailComponent,
        ProducersDialogComponent,
        ProducersDeleteDialogComponent,
        ProducersPopupComponent,
        ProducersDeletePopupComponent,
    ],
    entryComponents: [
        ProducersComponent,
        ProducersDialogComponent,
        ProducersPopupComponent,
        ProducersDeleteDialogComponent,
        ProducersDeletePopupComponent,
    ],
    providers: [
        ProducersService,
        ProducersPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopProducersModule {}

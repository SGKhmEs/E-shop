import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    MediaService,
    MediaPopupService,
    MediaComponent,
    MediaDetailComponent,
    MediaDialogComponent,
    MediaPopupComponent,
    MediaDeletePopupComponent,
    MediaDeleteDialogComponent,
    mediaRoute,
    mediaPopupRoute,
} from './';

const ENTITY_STATES = [
    ...mediaRoute,
    ...mediaPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MediaComponent,
        MediaDetailComponent,
        MediaDialogComponent,
        MediaDeleteDialogComponent,
        MediaPopupComponent,
        MediaDeletePopupComponent,
    ],
    entryComponents: [
        MediaComponent,
        MediaDialogComponent,
        MediaPopupComponent,
        MediaDeleteDialogComponent,
        MediaDeletePopupComponent,
    ],
    providers: [
        MediaService,
        MediaPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopMediaModule {}

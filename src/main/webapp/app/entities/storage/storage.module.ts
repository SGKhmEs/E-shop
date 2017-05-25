import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    StorageService,
    StoragePopupService,
    StorageComponent,
    StorageDetailComponent,
    StorageDialogComponent,
    StoragePopupComponent,
    StorageDeletePopupComponent,
    StorageDeleteDialogComponent,
    storageRoute,
    storagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...storageRoute,
    ...storagePopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        StorageComponent,
        StorageDetailComponent,
        StorageDialogComponent,
        StorageDeleteDialogComponent,
        StoragePopupComponent,
        StorageDeletePopupComponent,
    ],
    entryComponents: [
        StorageComponent,
        StorageDialogComponent,
        StoragePopupComponent,
        StorageDeleteDialogComponent,
        StorageDeletePopupComponent,
    ],
    providers: [
        StorageService,
        StoragePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopStorageModule {}

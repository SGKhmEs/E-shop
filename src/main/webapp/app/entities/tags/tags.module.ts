import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    TagsService,
    TagsPopupService,
    TagsComponent,
    TagsDetailComponent,
    TagsDialogComponent,
    TagsPopupComponent,
    TagsDeletePopupComponent,
    TagsDeleteDialogComponent,
    tagsRoute,
    tagsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tagsRoute,
    ...tagsPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TagsComponent,
        TagsDetailComponent,
        TagsDialogComponent,
        TagsDeleteDialogComponent,
        TagsPopupComponent,
        TagsDeletePopupComponent,
    ],
    entryComponents: [
        TagsComponent,
        TagsDialogComponent,
        TagsPopupComponent,
        TagsDeleteDialogComponent,
        TagsDeletePopupComponent,
    ],
    providers: [
        TagsService,
        TagsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopTagsModule {}

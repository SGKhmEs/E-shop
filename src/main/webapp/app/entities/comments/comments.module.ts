import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshopSharedModule } from '../../shared';
import {
    CommentsService,
    CommentsPopupService,
    CommentsComponent,
    CommentsDetailComponent,
    CommentsDialogComponent,
    CommentsPopupComponent,
    CommentsDeletePopupComponent,
    CommentsDeleteDialogComponent,
    commentsRoute,
    commentsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...commentsRoute,
    ...commentsPopupRoute,
];

@NgModule({
    imports: [
        EshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CommentsComponent,
        CommentsDetailComponent,
        CommentsDialogComponent,
        CommentsDeleteDialogComponent,
        CommentsPopupComponent,
        CommentsDeletePopupComponent,
    ],
    entryComponents: [
        CommentsComponent,
        CommentsDialogComponent,
        CommentsPopupComponent,
        CommentsDeleteDialogComponent,
        CommentsDeletePopupComponent,
    ],
    providers: [
        CommentsService,
        CommentsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopCommentsModule {}

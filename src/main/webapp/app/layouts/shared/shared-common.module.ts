import { NgModule } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { WindowRef } from './tracker/window.service';
import {
    EshopSharedLibsModule,
    JhiAlertComponent,
    JhiAlertErrorComponent
} from './';

@NgModule({
    imports: [
        EshopSharedLibsModule
    ],
    declarations: [
        JhiAlertComponent,
        JhiAlertErrorComponent
    ],
    providers: [
        WindowRef,
        Title
    ],
    exports: [
        EshopSharedLibsModule,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ]
})
export class EshopSharedCommonModule {}

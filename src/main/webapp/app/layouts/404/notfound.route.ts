import {NotFoundComponent} from "./notfound.component";
import { Routes } from '@angular/router';

export const notfoundRouter: Routes = [
    {
        path: 'notfound',
        component: NotFoundComponent,
        outlet: 'notfound'
    }
    ];

import { ShopListComponent } from "./shop-list.component";
import {Route, Routes} from '@angular/router';

export const shopListRouter: Route =
    {
        path: 'shoplist',
        component: ShopListComponent
        // outlet: 'shoplist'
    };

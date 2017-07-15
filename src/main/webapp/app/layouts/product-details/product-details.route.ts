import {ProductDetailsComponent} from "./product-details.component";
import { Routes } from '@angular/router';

export const productDetailsRoute: Routes = [
    {
        path: 'shoplist/:id',
        component: ProductDetailsComponent
    }
    ];

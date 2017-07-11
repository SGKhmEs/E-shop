import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

//import { navbarRoute } from '../app.route';
import { errorRoute } from './';
import {sliderRoute} from "../app.route";

const LAYOUT_ROUTES = [
    //navbarRoute,
    sliderRoute,
    ...errorRoute
];

@NgModule({
    imports: [
        RouterModule.forRoot(LAYOUT_ROUTES, { useHash: true })
    ],
    exports: [
        RouterModule
    ]
})
export class LayoutRoutingModule {}

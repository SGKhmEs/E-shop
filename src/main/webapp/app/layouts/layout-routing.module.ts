import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

//import { navbarRoute } from '../app.route';
import { errorRoute } from './error/error.route';
import {sliderRoute} from "../app.route";
import {notfoundRouter} from "./404/notfound.route";

const LAYOUT_ROUTES = [
    //navbarRoute,
    sliderRoute,
    ...notfoundRouter,
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

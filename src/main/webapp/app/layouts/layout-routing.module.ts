import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

//import { navbarRoute } from '../app.route';
import { errorRoute } from './error/error.route';
import {sliderRoute} from "../app.route";
import {notfoundRouter} from "./404/notfound.route";
import {shopListRouter} from "./shop-list/shop-list.route";
import {aboutUsRoute} from "./aboutUs/aboutUs.route";
import {contactRoute} from "./contact/contact.route";

const LAYOUT_ROUTES = [
    //navbarRoute,
    shopListRouter,
  
    ...notfoundRouter,
    ...errorRoute,
    ...aboutUsRoute,
    //sliderRoute,
   
    ...contactRoute
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

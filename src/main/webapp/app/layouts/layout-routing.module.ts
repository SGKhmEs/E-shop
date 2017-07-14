import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

//import { navbarRoute } from '../app.route';
import { errorRoute } from './error/error.route';
import {sliderRoute} from "../app.route";
import {notfoundRouter} from "./404/notfound.route";
import {shopListRouter} from "./shop-list/shop-list.route";
import {aboutUsRoute} from "./aboutUs/aboutUs.route";
import {contactRoute} from "./contact/contact.route";
import {faqRoute} from "./FAQ/faq.route";
import {checkoutRoute} from "./checkout/checkout.route";
import {indexRoute} from "./index/index.route"


const LAYOUT_ROUTES = [
    //navbarRoute,
    shopListRouter,

    ...notfoundRouter,
    ...errorRoute,
    ...aboutUsRoute,
    //sliderRoute,
    ...contactRoute,
    ...faqRoute,
    ...contactRoute,
    ...checkoutRoute,
    ...indexRoute
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

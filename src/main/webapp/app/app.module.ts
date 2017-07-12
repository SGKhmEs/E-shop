import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { EshopSharedModule, UserRouteAccessService } from './shared';
import { EshopHomeModule } from './home/home.module';
import { EshopAdminModule } from './admin/admin.module';
import { EshopAccountModule } from './account/account.module';
import { EshopEntityModule } from './entities/entity.module';

import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    LayoutRoutingModule,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';

import {HeaderComponent} from './layouts/header/header.component';
import {BrandComponent} from './layouts/brand/brand.component';
import {FeatureComponent} from './layouts/feature/feature.component';
import {SliderComponent} from './layouts/slider/slider.component';
import {ShopListComponent} from './layouts/shop-list/shop-list.component';
import {NotFoundComponent} from "./layouts/404/notfound.component";
import {CategoryComponent} from './layouts/shop-list/category/category.component';
import {PriceComponent} from './layouts/shop-list/price/price.component';
import {TagComponent} from './layouts/shop-list/tag/tag.component';
import {FilterComponent} from './layouts/shop-list/filter/filter.component';
import {ProductsComponent} from "./layouts/shop-list/products/products.component";
import {ProductItemComponent} from "./layouts/shop-list/products/product-item/product-item.component";
import {ProductsService} from "./layouts/shop-list/products/products.service";


@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        EshopSharedModule,
        EshopHomeModule,
        EshopAdminModule,
        EshopAccountModule,
        EshopEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        HeaderComponent,
        NavbarComponent,
        SliderComponent,
        ErrorComponent,
        PageRibbonComponent,
        BrandComponent,
        FeatureComponent,
        FooterComponent,
        ShopListComponent,
        NotFoundComponent,
        CategoryComponent,
        PriceComponent,
        TagComponent,
        FilterComponent,
        ProductsComponent,
        ProductItemComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService,
        ProductsService
    ],
    bootstrap: [ JhiMainComponent, HeaderComponent, NavbarComponent,FooterComponent ]
})
export class EshopAppModule {}

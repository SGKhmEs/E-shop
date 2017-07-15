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
import {TitleComponent} from './layouts/aboutUs/title/title.component';
import {AboutAreaComponent} from './layouts/aboutUs/aboutArea/aboutArea.component';
import {DevelopersComponent} from './layouts/aboutUs/developers/developers.component';
import {AboutUsComponent} from './layouts/aboutUs/aboutUs.component';
import {ContactComponent} from "./layouts/contact/contact.component"
import {ContactUsComponent} from "./layouts/contact/contactUs/contactus.component";
import {ContactTitleComponent} from "./layouts/contact/contact.title/title.component";
import { AgmCoreModule } from '@agm/core';
import {GoogleMapComponent} from "./layouts/contact/googleMaps/googleMaps.component";
import {FaqTitleComponent} from './layouts/FAQ/faqTitle/faqTitle.component';
import {QuestionComponent} from './layouts/FAQ/question/question.component';
import {FAQComponent} from './layouts/FAQ/faq.component';
import {CheckoutTitleComponent} from './layouts/checkout/checkoutTitle/checkoutTitle.component';
import {CheckoutAreaComponent} from './layouts/checkout/checkoutArea/checkoutArea.component';
import {CheckoutComponent} from './layouts/checkout/checkout.component';
import {WishListComponent} from "./layouts/wishlist/wish-lish.component";
import {BucketComponent} from "./layouts/bucket/bucket.component";
import {JewelryMagicComponent} from './layouts/jewelrymagic/jewelrymagic.component';
import {IndexPicturesComponent} from "./layouts/indexPictures/indexPictures.component";
import {ProductDetailsComponent} from "./layouts/product-details/product-details.component";

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
        AgmCoreModule.forRoot({
            apiKey: 'libraries=places&key=AIzaSyByA7Kxa-fMN2b38G_YcRBVLjsjarFO0Lc'
        })
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        HeaderComponent,
        NavbarComponent,
       // SliderComponent,
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
        ProductItemComponent,
        TitleComponent,
        AboutAreaComponent,
        DevelopersComponent,
        AboutUsComponent,
        ContactComponent,
        ContactUsComponent,
        ContactTitleComponent,
        GoogleMapComponent,
        FaqTitleComponent,
        QuestionComponent,
        FAQComponent,
        CheckoutTitleComponent,
        CheckoutAreaComponent,
        CheckoutComponent,
        WishListComponent,
        BucketComponent,
        JewelryMagicComponent,
        IndexPicturesComponent,
        ProductDetailsComponent
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

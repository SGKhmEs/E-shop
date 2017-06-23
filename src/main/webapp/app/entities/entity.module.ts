import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { EshopCustomerModule } from './customer/customer.module';
import { EshopLoginOptionsModule } from './login-options/login-options.module';
import { EshopStaticPagesModule } from './static-pages/static-pages.module';
import { EshopManagerModule } from './manager/manager.module';
import { EshopBucketModule } from './bucket/bucket.module';
import { EshopAddressShippingModule } from './address-shipping/address-shipping.module';
import { EshopSeenModule } from './seen/seen.module';
import { EshopWishListModule } from './wish-list/wish-list.module';
import { EshopAddressModule } from './address/address.module';
import { EshopAvatarModule } from './avatar/avatar.module';
import { EshopPersonalInformationModule } from './personal-information/personal-information.module';
import { EshopProductsModule } from './products/products.module';
import { EshopTagsModule } from './tags/tags.module';
import { EshopConsignmentModule } from './consignment/consignment.module';
import { EshopStorageModule } from './storage/storage.module';
import { EshopProducersModule } from './producers/producers.module';
import { EshopCategoryModule } from './category/category.module';
import { EshopSubCategoryModule } from './sub-category/sub-category.module';
import { EshopMediaModule } from './media/media.module';
import { EshopCommentsModule } from './comments/comments.module';
import { EshopOptionsModule } from './options/options.module';
import { EshopTagForProductModule } from './tag-for-product/tag-for-product.module';
import { EshopProductInBucketModule } from './product-in-bucket/product-in-bucket.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        EshopCustomerModule,
        EshopLoginOptionsModule,
        EshopStaticPagesModule,
        EshopManagerModule,
        EshopBucketModule,
        EshopAddressShippingModule,
        EshopSeenModule,
        EshopWishListModule,
        EshopAddressModule,
        EshopAvatarModule,
        EshopPersonalInformationModule,
        EshopProductsModule,
        EshopTagsModule,
        EshopConsignmentModule,
        EshopStorageModule,
        EshopProducersModule,
        EshopCategoryModule,
        EshopSubCategoryModule,
        EshopMediaModule,
        EshopCommentsModule,
        EshopOptionsModule,
        EshopTagForProductModule,
        EshopProductInBucketModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopEntityModule {}

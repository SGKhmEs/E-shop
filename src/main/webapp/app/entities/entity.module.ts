import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { EshopCustomerModule } from './customer/customer.module';
import { EshopCustomerRoomModule } from './customer-room/customer-room.module';
import { EshopCommentsModule } from './comments/comments.module';
import { EshopPersonalInformationModule } from './personal-information/personal-information.module';
import { EshopHistoryOrderModule } from './history-order/history-order.module';
import { EshopAddressShippingModule } from './address-shipping/address-shipping.module';
import { EshopConfirmModule } from './confirm/confirm.module';
import { EshopLoginOptionsModule } from './login-options/login-options.module';
import { EshopWishListModule } from './wish-list/wish-list.module';
import { EshopAddressModule } from './address/address.module';
import { EshopSessionIdModule } from './session-id/session-id.module';
import { EshopSeenModule } from './seen/seen.module';
import { EshopBucketModule } from './bucket/bucket.module';
import { EshopAvatarModule } from './avatar/avatar.module';
import { EshopOptionsModule } from './options/options.module';
import { EshopTypeModule } from './type/type.module';
import { EshopValueModule } from './value/value.module';
import { EshopProductsModule } from './products/products.module';
import { EshopCategoryModule } from './category/category.module';
import { EshopSubCategoryModule } from './sub-category/sub-category.module';
import { EshopMediaModule } from './media/media.module';
import { EshopTagsModule } from './tags/tags.module';
import { EshopConsignmentModule } from './consignment/consignment.module';
import { EshopStorageModule } from './storage/storage.module';
import { EshopProducersModule } from './producers/producers.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        EshopCustomerModule,
        EshopCustomerRoomModule,
        EshopCommentsModule,
        EshopPersonalInformationModule,
        EshopHistoryOrderModule,
        EshopAddressShippingModule,
        EshopConfirmModule,
        EshopLoginOptionsModule,
        EshopWishListModule,
        EshopAddressModule,
        EshopSessionIdModule,
        EshopSeenModule,
        EshopBucketModule,
        EshopAvatarModule,
        EshopOptionsModule,
        EshopTypeModule,
        EshopValueModule,
        EshopProductsModule,
        EshopCategoryModule,
        EshopSubCategoryModule,
        EshopMediaModule,
        EshopTagsModule,
        EshopConsignmentModule,
        EshopStorageModule,
        EshopProducersModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EshopEntityModule {}

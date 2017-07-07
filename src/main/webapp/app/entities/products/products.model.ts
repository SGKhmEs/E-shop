<<<<<<< HEAD
<<<<<<< HEAD
import { Media } from '../media';
import { Comments } from '../comments';
import { ProductInBucket } from '../product-in-bucket';
import { TagForProduct } from '../tag-for-product';
import { Consignment } from '../consignment';
import { SubCategory } from '../sub-category';
=======
>>>>>>> with_entities
export class Products {
=======
import { BaseEntity } from './../../shared';

export class Products implements BaseEntity {
>>>>>>> creatingDtos
    constructor(
        public id?: number,
        public name?: string,
<<<<<<< HEAD
        public price?: number,
        public sale?: number,
        public rating?: number,
        public fresh?: boolean,
<<<<<<< HEAD
        public media?: Media,
        public comments?: Comments,
        public productInBucket?: ProductInBucket,
        public tagForProduct?: TagForProduct,
        public consignment?: Consignment,
        public subCategory?: SubCategory,
=======
        public sale?: string,
        public rating?: number,
        public fresh?: boolean,
        public wishListId?: number,
        public seenId?: number,
        public bucketId?: number,
        public commentsId?: number,
        public consignmentId?: number,
        public subCategoryId?: number,
        public mediaId?: number,
        public tagsId?: number,
>>>>>>> with_entities
=======
        public description?: string,
        public options?: BaseEntity,
        public media?: BaseEntity[],
        public comments?: BaseEntity[],
        public productInBuckets?: BaseEntity[],
        public tagForProducts?: BaseEntity[],
        public consignment?: BaseEntity,
        public subCategory?: BaseEntity,
>>>>>>> creatingDtos
    ) {
        this.fresh = false;
    }
}

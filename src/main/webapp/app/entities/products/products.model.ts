import { Media } from '../media';
import { Comments } from '../comments';
import { ProductInBucket } from '../product-in-bucket';
import { TagForProduct } from '../tag-for-product';
import { Consignment } from '../consignment';
import { SubCategory } from '../sub-category';
export class Products {
    constructor(
        public id?: number,
        public name?: string,
        public price?: number,
        public sale?: string,
        public rating?: number,
        public fresh?: boolean,
        public media?: Media,
        public comments?: Comments,
        public productInBucket?: ProductInBucket,
        public tagForProduct?: TagForProduct,
        public consignment?: Consignment,
        public subCategory?: SubCategory,
    ) {
        this.fresh = false;
    }
}

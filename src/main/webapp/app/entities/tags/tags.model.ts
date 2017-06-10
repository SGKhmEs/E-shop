import { TagForProduct } from '../tag-for-product';
export class Tags {
    constructor(
        public id?: number,
        public name?: string,
        public tagForProduct?: TagForProduct,
    ) {
    }
}

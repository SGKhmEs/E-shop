import { BaseEntity } from './../../shared';

export class ProductInBucket implements BaseEntity {
    constructor(
        public id?: number,
        public bucketId?: number,
        public productsId?: number,
    ) {
    }
}

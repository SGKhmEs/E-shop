import { BaseEntity } from './../../shared';

export class ProductInBucket implements BaseEntity {
    constructor(
        public id?: number,
        public bucket?: BaseEntity,
        public products?: BaseEntity,
    ) {
    }
}

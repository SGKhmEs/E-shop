import { BaseEntity } from './../../shared';

export class WishList implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public customer?: BaseEntity,
        public product?: BaseEntity,
    ) {
    }
}

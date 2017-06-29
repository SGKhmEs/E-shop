import { BaseEntity } from './../../shared';

export class TagForProduct implements BaseEntity {
    constructor(
        public id?: number,
        public productsId?: number,
        public tagsId?: number,
    ) {
    }
}

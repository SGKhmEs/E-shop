import { BaseEntity } from './../../shared';

export class Products implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public price?: number,
        public sale?: number,
        public rating?: number,
        public fresh?: boolean,
        public description?: string,
        public options?: BaseEntity,
        public media?: BaseEntity[],
        public comments?: BaseEntity[],
        public productInBuckets?: BaseEntity[],
        public tagForProducts?: BaseEntity[],
        public consignment?: BaseEntity,
        public subCategory?: BaseEntity,
    ) {
        this.fresh = false;
    }
}

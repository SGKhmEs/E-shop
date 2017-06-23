import { BaseEntity } from './../../shared';

export class SubCategory implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public category?: BaseEntity,
        public products?: BaseEntity[],
    ) {
    }
}

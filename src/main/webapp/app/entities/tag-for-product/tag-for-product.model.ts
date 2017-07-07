import { BaseEntity } from './../../shared';

export class TagForProduct implements BaseEntity {
    constructor(
        public id?: number,
        public products?: BaseEntity,
        public tags?: BaseEntity,
    ) {
    }
}

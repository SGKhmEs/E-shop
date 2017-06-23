import { BaseEntity } from './../../shared';

export class Seen implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public customer?: BaseEntity,
        public products?: BaseEntity,
    ) {
    }
}

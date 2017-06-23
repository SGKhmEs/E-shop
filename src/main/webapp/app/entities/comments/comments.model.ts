import { BaseEntity } from './../../shared';

export class Comments implements BaseEntity {
    constructor(
        public id?: number,
        public comments?: string,
        public date?: any,
        public customer?: BaseEntity,
        public products?: BaseEntity,
    ) {
    }
}

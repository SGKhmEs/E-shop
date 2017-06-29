import { BaseEntity } from './../../shared';

export class Consignment implements BaseEntity {
    constructor(
        public id?: number,
        public price?: number,
        public products?: BaseEntity[],
        public storageId?: number,
        public producersId?: number,
    ) {
    }
}

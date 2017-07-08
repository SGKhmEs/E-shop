import { BaseEntity } from './../../shared';

export class CustomerAccount implements BaseEntity {
    constructor(
        public id?: number,
        public createdAt?: any,
        public userId?: number,
        public customerId?: number,
    ) {
    }
}

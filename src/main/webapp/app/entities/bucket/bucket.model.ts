import { BaseEntity } from './../../shared';

const enum Status {
    'WAIT',
    'PROCESSING',
    'CHECKED',
    'FILLED',
    'SUCCESS',
    'FAILURE',
    'REJECT'
}

export class Bucket implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public date?: any,
        public sum?: number,
        public orderNumber?: number,
        public count?: number,
        public status?: Status,
        public consignmentNote?: string,
        public managerId?: number,
        public addressShippingId?: number,
        public productInBuckets?: BaseEntity[],
        public customerId?: number,
    ) {
    }
}

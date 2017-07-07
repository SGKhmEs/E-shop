<<<<<<< HEAD
<<<<<<< HEAD
=======
import { BaseEntity } from './../../shared';
>>>>>>> creatingDtos

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
<<<<<<< HEAD
        public manager?: Manager,
        public addressShipping?: AddressShipping,
        public productInBucket?: ProductInBucket,
        public customer?: Customer,
=======
export class Bucket {
    constructor(
        public id?: number,
        public data?: any,
        public productId?: number,
>>>>>>> with_entities
=======
        public manager?: BaseEntity,
        public addressShipping?: BaseEntity,
        public productInBuckets?: BaseEntity[],
        public customer?: BaseEntity,
>>>>>>> creatingDtos
    ) {
    }
}

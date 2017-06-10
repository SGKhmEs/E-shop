
const enum Status {
    'WAIT',
    'PURCHASED',
    'SUCCESS',
    'FAILURE',
    'REJECT'

};
import { Manager } from '../manager';
import { AddressShipping } from '../address-shipping';
import { ProductInBucket } from '../product-in-bucket';
import { Customer } from '../customer';
export class Bucket {
    constructor(
        public id?: number,
        public name?: string,
        public data?: any,
        public sum?: number,
        public orderNumber?: number,
        public count?: number,
        public status?: Status,
        public consignmentNote?: string,
        public manager?: Manager,
        public addressShipping?: AddressShipping,
        public productInBucket?: ProductInBucket,
        public customer?: Customer,
    ) {
    }
}

import { Bucket } from '../bucket';
import { Products } from '../products';
export class ProductInBucket {
    constructor(
        public id?: number,
        public bucket?: Bucket,
        public products?: Products,
    ) {
    }
}

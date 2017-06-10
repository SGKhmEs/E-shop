import { Customer } from '../customer';
import { Products } from '../products';
export class WishList {
    constructor(
        public id?: number,
        public data?: any,
        public customer?: Customer,
        public product?: Products,
    ) {
    }
}

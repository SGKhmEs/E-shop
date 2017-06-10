import { Customer } from '../customer';
import { Products } from '../products';
export class Comments {
    constructor(
        public id?: number,
        public comments?: string,
        public data?: any,
        public customer?: Customer,
        public products?: Products,
    ) {
    }
}

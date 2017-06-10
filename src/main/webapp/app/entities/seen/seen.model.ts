import { Customer } from '../customer';
import { Products } from '../products';
export class Seen {
    constructor(
        public id?: number,
        public data?: any,
        public customer?: Customer,
        public products?: Products,
    ) {
    }
}

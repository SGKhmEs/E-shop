<<<<<<< HEAD
import { Customer } from '../customer';
import { Products } from '../products';
export class WishList {
    constructor(
        public id?: number,
        public data?: any,
        public customer?: Customer,
        public product?: Products,
=======
export class WishList {
    constructor(
        public id?: number,
        public wishsName?: string,
        public data?: any,
        public productId?: number,
>>>>>>> with_entities
    ) {
    }
}

<<<<<<< HEAD
import { Customer } from '../customer';
import { Products } from '../products';
=======
>>>>>>> with_entities
export class Comments {
    constructor(
        public id?: number,
        public comments?: string,
        public data?: any,
<<<<<<< HEAD
        public customer?: Customer,
        public products?: Products,
=======
        public customerRoomId?: number,
        public customerId?: number,
        public productsId?: number,
>>>>>>> with_entities
    ) {
    }
}

<<<<<<< HEAD
import { Products } from '../products';
import { Storage } from '../storage';
import { Producers } from '../producers';
=======
>>>>>>> with_entities
export class Consignment {
    constructor(
        public id?: number,
        public price?: number,
<<<<<<< HEAD
        public products?: Products,
        public storage?: Storage,
        public producers?: Producers,
=======
        public productsId?: number,
        public storageId?: number,
        public producersId?: number,
>>>>>>> with_entities
    ) {
    }
}

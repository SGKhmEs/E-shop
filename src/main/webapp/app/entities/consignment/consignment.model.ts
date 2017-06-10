import { Products } from '../products';
import { Storage } from '../storage';
import { Producers } from '../producers';
export class Consignment {
    constructor(
        public id?: number,
        public price?: number,
        public products?: Products,
        public storage?: Storage,
        public producers?: Producers,
    ) {
    }
}

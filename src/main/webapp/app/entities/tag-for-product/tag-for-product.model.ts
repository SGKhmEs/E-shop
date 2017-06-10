import { Products } from '../products';
import { Tags } from '../tags';
export class TagForProduct {
    constructor(
        public id?: number,
        public products?: Products,
        public tags?: Tags,
    ) {
    }
}

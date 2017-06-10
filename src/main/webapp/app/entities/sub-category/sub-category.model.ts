import { Category } from '../category';
import { Options } from '../options';
import { Products } from '../products';
export class SubCategory {
    constructor(
        public id?: number,
        public name?: string,
        public category?: Category,
        public options?: Options,
        public product?: Products,
    ) {
    }
}

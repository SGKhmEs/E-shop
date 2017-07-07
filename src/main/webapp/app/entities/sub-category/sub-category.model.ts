<<<<<<< HEAD
<<<<<<< HEAD
import { Category } from '../category';
import { Options } from '../options';
import { Products } from '../products';
=======
>>>>>>> with_entities
export class SubCategory {
    constructor(
        public id?: number,
        public name?: string,
<<<<<<< HEAD
        public category?: Category,
        public options?: Options,
        public product?: Products,
=======
        public categoryId?: number,
        public optionsId?: number,
        public productId?: number,
>>>>>>> with_entities
=======
import { BaseEntity } from './../../shared';

export class SubCategory implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public category?: BaseEntity,
        public products?: BaseEntity[],
>>>>>>> creatingDtos
    ) {
    }
}

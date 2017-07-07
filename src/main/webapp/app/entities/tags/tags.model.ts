<<<<<<< HEAD
<<<<<<< HEAD
import { TagForProduct } from '../tag-for-product';
=======
>>>>>>> with_entities
export class Tags {
    constructor(
        public id?: number,
        public name?: string,
<<<<<<< HEAD
        public tagForProduct?: TagForProduct,
=======
        public productId?: number,
>>>>>>> with_entities
=======
import { BaseEntity } from './../../shared';

export class Tags implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public tagForProducts?: BaseEntity[],
>>>>>>> creatingDtos
    ) {
    }
}

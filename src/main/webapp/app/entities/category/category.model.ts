<<<<<<< HEAD
<<<<<<< HEAD
import { SubCategory } from '../sub-category';
=======
>>>>>>> with_entities
export class Category {
    constructor(
        public id?: number,
        public name?: string,
<<<<<<< HEAD
        public subCat?: SubCategory,
=======
        public subCatId?: number,
>>>>>>> with_entities
=======
import { BaseEntity } from './../../shared';

export class Category implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public subCats?: BaseEntity[],
>>>>>>> creatingDtos
    ) {
    }
}

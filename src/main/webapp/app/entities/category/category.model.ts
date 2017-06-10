import { SubCategory } from '../sub-category';
export class Category {
    constructor(
        public id?: number,
        public name?: string,
        public subCat?: SubCategory,
    ) {
    }
}

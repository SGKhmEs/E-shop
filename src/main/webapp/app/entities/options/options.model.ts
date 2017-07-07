<<<<<<< HEAD
import { Value } from '../value';
import { Type } from '../type';
=======
>>>>>>> with_entities
export class Options {
    constructor(
        public id?: number,
        public name?: string,
        public level?: number,
<<<<<<< HEAD
        public value?: Value,
        public type?: Type,
=======
        public valueId?: number,
        public typeId?: number,
        public subCategoryId?: number,
>>>>>>> with_entities
    ) {
    }
}

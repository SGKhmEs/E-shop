<<<<<<< HEAD
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
=======
import { BaseEntity } from './../../shared';

export class Options implements BaseEntity {
    constructor(
        public id?: number,
        public color?: number,
        public weight?: number,
        public metal?: string,
        public size?: number,
        public length?: number,
>>>>>>> creatingDtos
    ) {
    }
}

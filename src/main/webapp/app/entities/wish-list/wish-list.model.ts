<<<<<<< HEAD
<<<<<<< HEAD
import { Customer } from '../customer';
import { Products } from '../products';
export class WishList {
    constructor(
        public id?: number,
        public data?: any,
        public customer?: Customer,
        public product?: Products,
=======
export class WishList {
    constructor(
        public id?: number,
        public wishsName?: string,
        public data?: any,
        public productId?: number,
>>>>>>> with_entities
=======
import { BaseEntity } from './../../shared';

export class WishList implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public customer?: BaseEntity,
        public product?: BaseEntity,
>>>>>>> creatingDtos
    ) {
    }
}

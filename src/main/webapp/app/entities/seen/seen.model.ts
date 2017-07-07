<<<<<<< HEAD
<<<<<<< HEAD
import { Customer } from '../customer';
import { Products } from '../products';
=======
>>>>>>> with_entities
export class Seen {
    constructor(
        public id?: number,
        public data?: any,
<<<<<<< HEAD
        public customer?: Customer,
        public products?: Products,
=======
        public productId?: number,
>>>>>>> with_entities
=======
import { BaseEntity } from './../../shared';

export class Seen implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public customer?: BaseEntity,
        public products?: BaseEntity,
>>>>>>> creatingDtos
    ) {
    }
}

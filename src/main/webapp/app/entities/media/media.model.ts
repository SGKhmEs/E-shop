<<<<<<< HEAD
<<<<<<< HEAD
import { Products } from '../products';
=======
>>>>>>> with_entities
export class Media {
=======
import { BaseEntity } from './../../shared';

export class Media implements BaseEntity {
>>>>>>> creatingDtos
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public contentType?: string,
        public location?: string,
        public size?: string,
<<<<<<< HEAD
<<<<<<< HEAD
        public products?: Products,
=======
        public commentsId?: number,
        public productId?: number,
>>>>>>> with_entities
=======
        public products?: BaseEntity,
>>>>>>> creatingDtos
    ) {
    }
}

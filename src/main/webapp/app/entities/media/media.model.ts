<<<<<<< HEAD
import { Products } from '../products';
=======
>>>>>>> with_entities
export class Media {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public contentType?: string,
        public location?: string,
        public size?: string,
<<<<<<< HEAD
        public products?: Products,
=======
        public commentsId?: number,
        public productId?: number,
>>>>>>> with_entities
    ) {
    }
}

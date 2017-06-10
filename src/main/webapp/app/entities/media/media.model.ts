import { Products } from '../products';
export class Media {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public contentType?: string,
        public location?: string,
        public size?: string,
        public products?: Products,
    ) {
    }
}

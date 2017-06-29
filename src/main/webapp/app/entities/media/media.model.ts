import { BaseEntity } from './../../shared';

export class Media implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public contentType?: string,
        public location?: string,
        public size?: string,
        public productsId?: number,
    ) {
    }
}

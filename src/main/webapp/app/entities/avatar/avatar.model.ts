import { BaseEntity } from './../../shared';

export class Avatar implements BaseEntity {
    constructor(
        public id?: number,
<<<<<<< HEAD
        public usersImageContentType?: string,
=======
>>>>>>> with_entities
        public usersImage?: any,
    ) {
    }
}

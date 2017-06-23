import { BaseEntity } from './../../shared';

export class Avatar implements BaseEntity {
    constructor(
        public id?: number,
        public usersImageContentType?: string,
        public usersImage?: any,
    ) {
    }
}

import { BaseEntity } from './../../shared';

export class LoginOptions implements BaseEntity {
    constructor(
        public id?: number,
        public login?: string,
        public password?: string,
    ) {
    }
}

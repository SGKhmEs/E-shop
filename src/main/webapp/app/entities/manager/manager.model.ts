import { BaseEntity } from './../../shared';

const enum Roles {
    'SMM',
    'MODERATOR',
    'ADMIN',
    'OWNER'
}

export class Manager implements BaseEntity {
    constructor(
        public id?: number,
        public roles?: Roles,
        public loginOptions?: BaseEntity,
        public personalInfo?: BaseEntity,
        public avatar?: BaseEntity,
        public manegers?: BaseEntity[],
    ) {
    }
}

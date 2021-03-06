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
        public loginOptionsId?: number,
        public personalInfoId?: number,
        public avatarId?: number,
        public manegers?: BaseEntity[],
    ) {
    }
}

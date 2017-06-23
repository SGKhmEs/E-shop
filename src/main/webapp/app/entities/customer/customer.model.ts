import { BaseEntity } from './../../shared';

const enum SocialConnect {
    'DEFAULT',
    'GOOGLE',
    'FACEBOOK',
    'TWITTER'
}

export class Customer implements BaseEntity {
    constructor(
        public id?: number,
        public subScription?: boolean,
        public sosialConnect?: SocialConnect,
        public sessionId?: string,
        public loginOptions?: BaseEntity,
        public address?: BaseEntity,
        public personalInfo?: BaseEntity,
        public avatar?: BaseEntity,
        public seens?: BaseEntity[],
        public wishLists?: BaseEntity[],
        public comments?: BaseEntity[],
    ) {
        this.subScription = false;
    }
}

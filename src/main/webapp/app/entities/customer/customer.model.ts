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
        public loginOptionsId?: number,
        public addressId?: number,
        public personalInfoId?: number,
        public avatarId?: number,
        public seens?: BaseEntity[],
        public wishLists?: BaseEntity[],
        public comments?: BaseEntity[],
    ) {
        this.subScription = false;
    }
}

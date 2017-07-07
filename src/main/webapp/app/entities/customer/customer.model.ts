<<<<<<< HEAD
<<<<<<< HEAD
import { LoginOptions } from '../login-options';
import { CustomerRoom } from '../customer-room';
import { Seen } from '../seen';
import { WishList } from '../wish-list';
import { Comments } from '../comments';
export class Customer {
=======
import { BaseEntity } from './../../shared';

const enum SocialConnect {
    'DEFAULT',
    'GOOGLE',
    'FACEBOOK',
    'TWITTER'
}

export class Customer implements BaseEntity {
>>>>>>> creatingDtos
    constructor(
        public id?: number,
        public subScription?: boolean,
        public sosialConnect?: SocialConnect,
        public sessionId?: string,
<<<<<<< HEAD
        public loginOptions?: LoginOptions,
        public customerRoom?: CustomerRoom,
        public seen?: Seen,
        public wishList?: WishList,
        public comments?: Comments,
=======

const enum Roles {
    'USER',
    'MODERATOR',
    'ADMINISTRATOR',
    'OWNER'

};
export class Customer {
    constructor(
        public id?: number,
        public roles?: Roles,
        public loginOptionsId?: number,
        public confirmId?: number,
        public userRoomId?: number,
        public customerId?: number,
>>>>>>> with_entities
=======
        public loginOptions?: BaseEntity,
        public address?: BaseEntity,
        public personalInfo?: BaseEntity,
        public avatar?: BaseEntity,
        public seens?: BaseEntity[],
        public wishLists?: BaseEntity[],
        public comments?: BaseEntity[],
>>>>>>> creatingDtos
    ) {
        this.subScription = false;
    }
}

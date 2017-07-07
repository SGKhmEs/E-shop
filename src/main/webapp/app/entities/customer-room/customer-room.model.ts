
const enum SocialConnect {
    'DEFAULT',
    'GOOGLE',
    'FACEBOOK',
    'VK',
    'TWITTER',
    'OK'

};
<<<<<<< HEAD
import { Address } from '../address';
import { PersonalInformation } from '../personal-information';
=======
>>>>>>> with_entities
export class CustomerRoom {
    constructor(
        public id?: number,
        public subScription?: boolean,
        public sosialConnect?: SocialConnect,
<<<<<<< HEAD
        public address?: Address,
        public personalInfo?: PersonalInformation,
=======
        public personalInfoId?: number,
        public wishListId?: number,
        public addressId?: number,
        public bucketId?: number,
        public seenId?: number,
        public historyOrderId?: number,
        public customerId?: number,
>>>>>>> with_entities
    ) {
        this.subScription = false;
    }
}

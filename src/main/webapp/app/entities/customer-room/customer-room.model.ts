
const enum SocialConnect {
    'DEFAULT',
    'GOOGLE',
    'FACEBOOK',
    'VK',
    'TWITTER',
    'OK'

};
import { Address } from '../address';
import { PersonalInformation } from '../personal-information';
export class CustomerRoom {
    constructor(
        public id?: number,
        public subScription?: boolean,
        public sosialConnect?: SocialConnect,
        public address?: Address,
        public personalInfo?: PersonalInformation,
    ) {
        this.subScription = false;
    }
}

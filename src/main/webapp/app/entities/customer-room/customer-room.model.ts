
const enum SocialConnect {
    'DEFAULT',
    'GOOGLE',
    'FACEBOOK',
    'VK',
    'TWITTER',
    'OK'

};
export class CustomerRoom {
    constructor(
        public id?: number,
        public subScription?: boolean,
        public sosialConnect?: SocialConnect,
        public personalInfoId?: number,
        public wishListId?: number,
        public addressId?: number,
        public bucketId?: number,
        public seenId?: number,
        public historyOrderId?: number,
        public customerId?: number,
    ) {
        this.subScription = false;
    }
}

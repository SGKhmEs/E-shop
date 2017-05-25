
const enum Sex {
    'MAN',
    'WOMAN'

};
export class PersonalInformation {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public middleName?: string,
        public sex?: Sex,
        public phone?: string,
        public email?: string,
        public dateBirth?: any,
        public avatarId?: number,
        public addressShippingId?: number,
    ) {
    }
}

import { BaseEntity } from './../../shared';

const enum Sex {
    'MAN',
    'WOMAN'
}

export class PersonalInformation implements BaseEntity {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public middleName?: string,
        public sex?: Sex,
        public phone?: string,
        public email?: string,
        public dateBirth?: any,
    ) {
    }
}

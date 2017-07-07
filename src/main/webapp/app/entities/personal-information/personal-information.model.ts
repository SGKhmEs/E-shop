import { BaseEntity } from './../../shared';

const enum Sex {
    'MAN',
    'WOMAN'
}

<<<<<<< HEAD
};
<<<<<<< HEAD
import { Avatar } from '../avatar';
=======
>>>>>>> with_entities
export class PersonalInformation {
=======
export class PersonalInformation implements BaseEntity {
>>>>>>> creatingDtos
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public middleName?: string,
        public sex?: Sex,
        public phone?: string,
        public email?: string,
        public dateBirth?: any,
<<<<<<< HEAD
<<<<<<< HEAD
        public avatar?: Avatar,
=======
        public avatarId?: number,
        public addressShippingId?: number,
>>>>>>> with_entities
=======
>>>>>>> creatingDtos
    ) {
    }
}

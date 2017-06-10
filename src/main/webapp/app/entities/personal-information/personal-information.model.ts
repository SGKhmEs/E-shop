
const enum Sex {
    'MAN',
    'WOMAN'

};
import { Avatar } from '../avatar';
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
        public avatar?: Avatar,
    ) {
    }
}

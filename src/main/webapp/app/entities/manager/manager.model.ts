
const enum Roles {
    'USER',
    'MODERATOR',
    'ADMINISTRATOR',
    'OWNER'

};
import { LoginOptions } from '../login-options';
import { PersonalInformation } from '../personal-information';
import { Bucket } from '../bucket';
export class Manager {
    constructor(
        public id?: number,
        public roles?: Roles,
        public loginOptions?: LoginOptions,
        public personalInfo?: PersonalInformation,
        public maneger?: Bucket,
    ) {
    }
}

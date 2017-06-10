import { Consignment } from '../consignment';
export class Producers {
    constructor(
        public id?: number,
        public name?: string,
        public country?: string,
        public consignment?: Consignment,
    ) {
    }
}

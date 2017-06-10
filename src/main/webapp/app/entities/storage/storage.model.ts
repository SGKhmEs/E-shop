import { Consignment } from '../consignment';
export class Storage {
    constructor(
        public id?: number,
        public name?: string,
        public actualite?: boolean,
        public quantity?: number,
        public consignment?: Consignment,
    ) {
        this.actualite = false;
    }
}

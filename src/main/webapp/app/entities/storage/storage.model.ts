<<<<<<< HEAD
import { Consignment } from '../consignment';
=======
>>>>>>> with_entities
export class Storage {
    constructor(
        public id?: number,
        public name?: string,
        public actualite?: boolean,
        public quantity?: number,
<<<<<<< HEAD
        public consignment?: Consignment,
=======
        public consignmentId?: number,
>>>>>>> with_entities
    ) {
        this.actualite = false;
    }
}

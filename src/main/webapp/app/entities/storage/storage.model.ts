<<<<<<< HEAD
<<<<<<< HEAD
import { Consignment } from '../consignment';
=======
>>>>>>> with_entities
export class Storage {
=======
import { BaseEntity } from './../../shared';

export class Storage implements BaseEntity {
>>>>>>> creatingDtos
    constructor(
        public id?: number,
        public name?: string,
        public actualite?: boolean,
        public quantity?: number,
<<<<<<< HEAD
<<<<<<< HEAD
        public consignment?: Consignment,
=======
        public consignmentId?: number,
>>>>>>> with_entities
=======
        public consignments?: BaseEntity[],
>>>>>>> creatingDtos
    ) {
        this.actualite = false;
    }
}

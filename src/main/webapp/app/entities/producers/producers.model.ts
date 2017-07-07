<<<<<<< HEAD
<<<<<<< HEAD
import { Consignment } from '../consignment';
=======
>>>>>>> with_entities
export class Producers {
=======
import { BaseEntity } from './../../shared';

export class Producers implements BaseEntity {
>>>>>>> creatingDtos
    constructor(
        public id?: number,
        public name?: string,
        public country?: string,
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
    }
}

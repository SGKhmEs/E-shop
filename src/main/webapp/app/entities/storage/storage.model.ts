import { BaseEntity } from './../../shared';

export class Storage implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public actualite?: boolean,
        public quantity?: number,
        public consignments?: BaseEntity[],
    ) {
        this.actualite = false;
    }
}

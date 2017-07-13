import { BaseEntity } from './../../shared';

export class Producers implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public country?: string,
        public consignments?: BaseEntity[],
    ) {
    }
}

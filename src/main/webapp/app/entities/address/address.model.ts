import { BaseEntity } from './../../shared';

export class Address implements BaseEntity {
    constructor(
        public id?: number,
        public country?: string,
        public city?: string,
        public state?: string,
        public region?: string,
        public street?: string,
        public building?: string,
        public appartment?: string,
    ) {
    }
}

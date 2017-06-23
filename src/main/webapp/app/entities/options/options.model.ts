import { BaseEntity } from './../../shared';

export class Options implements BaseEntity {
    constructor(
        public id?: number,
        public color?: number,
        public weight?: number,
        public metal?: string,
        public size?: number,
        public length?: number,
    ) {
    }
}

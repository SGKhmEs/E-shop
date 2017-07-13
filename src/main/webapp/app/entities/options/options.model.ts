import { BaseEntity } from './../../shared';

export class Options implements BaseEntity {
    constructor(
        public id?: number,
        public metal?: string,
        public color?: string,
        public stone?: string,
        public marking?: string,
        public weight?: number,
        public size?: number,
        public length?: number,
    ) {
    }
}

import { BaseEntity } from './../../shared';

export class Tags implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public tagForProducts?: BaseEntity[],
    ) {
    }
}

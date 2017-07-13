import { BaseEntity } from './../../shared';

export class ManagerAccount implements BaseEntity {
    constructor(
        public id?: number,
        public createdAt?: any,
        public userId?: number,
        public managerId?: number,
    ) {
    }
}

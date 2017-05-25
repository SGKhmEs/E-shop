
const enum Roles {
    'USER',
    'MODERATOR',
    'ADMINISTRATOR',
    'OWNER'

};
export class Customer {
    constructor(
        public id?: number,
        public roles?: Roles,
        public loginOptionsId?: number,
        public confirmId?: number,
        public userRoomId?: number,
        public customerId?: number,
    ) {
    }
}

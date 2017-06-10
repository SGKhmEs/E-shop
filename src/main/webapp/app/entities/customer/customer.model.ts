import { LoginOptions } from '../login-options';
import { CustomerRoom } from '../customer-room';
import { Seen } from '../seen';
import { WishList } from '../wish-list';
import { Comments } from '../comments';
export class Customer {
    constructor(
        public id?: number,
        public sessionId?: string,
        public loginOptions?: LoginOptions,
        public customerRoom?: CustomerRoom,
        public seen?: Seen,
        public wishList?: WishList,
        public comments?: Comments,
    ) {
    }
}

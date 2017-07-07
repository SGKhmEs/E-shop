
const enum Status {
    'SUCCESS',
    'FAILURE',
    'REJECT',
    'WAIT'

};
export class HistoryOrder {
    constructor(
        public id?: number,
        public orderNumber?: number,
        public date?: any,
        public count?: number,
        public sum?: number,
        public status?: Status,
        public consignmentNote?: string,
    ) {
    }
}

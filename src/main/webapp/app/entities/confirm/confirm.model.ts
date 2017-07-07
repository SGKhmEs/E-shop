
const enum Payment {
    'CASH',
    'CREDITCARD',
    'PAYMENTSYSTEM'

};
export class Confirm {
    constructor(
        public id?: number,
        public pay?: Payment,
    ) {
    }
}

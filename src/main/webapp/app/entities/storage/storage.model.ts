export class Storage {
    constructor(
        public id?: number,
        public name?: string,
        public actualite?: boolean,
        public quantity?: number,
        public consignmentId?: number,
    ) {
        this.actualite = false;
    }
}

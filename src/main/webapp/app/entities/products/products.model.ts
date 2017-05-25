export class Products {
    constructor(
        public id?: number,
        public name?: string,
        public sale?: string,
        public rating?: number,
        public fresh?: boolean,
        public wishListId?: number,
        public seenId?: number,
        public bucketId?: number,
        public commentsId?: number,
        public consignmentId?: number,
        public subCategoryId?: number,
        public mediaId?: number,
        public tagsId?: number,
    ) {
        this.fresh = false;
    }
}

import { BaseEntity } from './../../shared';

export class Products implements BaseEntity {
    constructor(
        // public id?: number,
        // public name?: string,
        // public price?: number,
        // public sale?: number,
        // public rating?: number,
        // public fresh?: boolean,
        // public description?: string,
        // public optionsId?: number,
        // public media?: BaseEntity[],
        // public comments?: BaseEntity[],
        // public productInBuckets?: BaseEntity[],
        // public tagForProducts?: BaseEntity[],
        // public consignmentId?: number,
        // public subCategoryId?: number,
        public id?: number,
        public name?: string,
        public description?: string,
        public fresh?: boolean,
        public mediaId?: number,
        public mediaDTO?: any[],
        public optionsDTO?: any[],
        public optionsId?: number,
        public price?: number,
        public rating?: number,
        public sale?: number,
        public subCategoryDTO?: any[],
        public subCategoryId?: number,
        public tagForProductsDTO?: any[],
        public commentsDTO?: any[],

    ) {
        this.fresh = false;
    }
}

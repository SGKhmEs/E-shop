package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ProductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Products and its DTO ProductsDTO.
 */
@Mapper(componentModel = "spring", uses = {WishListMapper.class, SeenMapper.class, BucketMapper.class, SubCategoryMapper.class, MediaMapper.class, TagsMapper.class, })
public interface ProductsMapper extends EntityMapper <ProductsDTO, Products> {
    @Mapping(source = "wishList.id", target = "wishListId")
    @Mapping(source = "seen.id", target = "seenId")
    @Mapping(source = "bucket.id", target = "bucketId")
    @Mapping(source = "subCategory.id", target = "subCategoryId")
    @Mapping(source = "media.id", target = "mediaId")
    @Mapping(source = "tags.id", target = "tagsId")
    ProductsDTO toDto(Products products); 
    @Mapping(source = "wishListId", target = "wishList")
    @Mapping(source = "seenId", target = "seen")
    @Mapping(source = "bucketId", target = "bucket")
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "consignments", ignore = true)
    @Mapping(source = "subCategoryId", target = "subCategory")
    @Mapping(source = "mediaId", target = "media")
    @Mapping(source = "tagsId", target = "tags")
    Products toEntity(ProductsDTO productsDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Products fromId(Long id) {
        if (id == null) {
            return null;
        }
        Products products = new Products();
        products.setId(id);
        return products;
    }
}

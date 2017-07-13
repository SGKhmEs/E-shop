package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ProductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Products and its DTO ProductsDTO.
 */
@Mapper(componentModel = "spring", uses = {OptionsMapper.class, ConsignmentMapper.class, SubCategoryMapper.class, CommentsMapper.class, MediaMapper.class, TagForProductMapper.class, })
public interface ProductsMapper extends EntityMapper <ProductsDTO, Products> {


    @Mapping(source = "options", target = "optionsDTO")
    @Mapping(source = "subCategory", target = "subCategoryDTO")
    @Mapping(source = "comments", target = "commentsDTO")
    @Mapping(source = "media", target = "mediaDTO")
    @Mapping(source = "tagForProducts", target = "tagForProductDTO")
    //@Mapping(source = "options.id", target = "optionsId")
    //@Mapping(source = "consignment.id", target = "consignmentId")
    //@Mapping(source = "subCategory.id", target = "subCategoryId")
    ProductsDTO toDto(Products products);

    /*
    * pay attention to it
    * */

    @Mapping(target = "media", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "productInBuckets", ignore = true)
    @Mapping(target = "tagForProducts", ignore = true)
    // @Mapping(source = "optionsId", target = "options")
    // @Mapping(source = "consignmentId", target = "consignment")
    // @Mapping(source = "subCategoryId", target = "subCategory")
    Products toEntity(ProductsDTO productsDTO);
    default Products fromId(Long id) {
        if (id == null) {
            return null;
        }
        Products products = new Products();
        products.setId(id);
        return products;
    }
}

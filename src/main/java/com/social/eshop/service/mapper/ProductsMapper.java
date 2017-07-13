package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ProductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Products and its DTO ProductsDTO.
 */
@Mapper(componentModel = "spring", uses = {OptionsMapper.class, ConsignmentMapper.class, SubCategoryMapper.class, CommentsMapper.class, MediaMapper.class, TagForProductMapper.class, })
public interface ProductsMapper extends EntityMapper <ProductsDTO, Products> {

    //  @Mapping(source = "consignment.id", target = "consignmentId")

    @Mapping(source = "options", target = "optionsDTO")
    @Mapping(source = "options.id", target = "optionsId")

    @Mapping(source = "subCategory", target = "subCategoryDTO")
    @Mapping(source = "subCategory.id", target = "subCategoryId")

    @Mapping(source = "comments", target = "commentsDTO")

    @Mapping(source = "media", target = "mediaDTO")
   // @Mapping(source = "media.id", target = "mediaId")

    @Mapping(source = "tagForProducts", target = "tagForProductDTO")
    ProductsDTO toDto(Products products);

    /*
    * pay attention to it
    * */
   // @Mapping(target = "options", ignore = true)
    @Mapping(target = "media", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "productInBuckets", ignore = true)
    @Mapping(target = "tagForProducts", ignore = true)
    @Mapping(source = "optionsId", target = "options")
    //@Mapping(source = "mediaId", target = "media")
   // @Mapping(source = "consignmentId", target = "consignment")
    @Mapping(source = "subCategoryId", target = "subCategory")
   // @Mapping(source = "tagForProductsId", target = "tagForProducts")
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

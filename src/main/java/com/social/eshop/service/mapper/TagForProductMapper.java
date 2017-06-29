package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.TagForProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TagForProduct and its DTO TagForProductDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductsMapper.class, TagsMapper.class, })
public interface TagForProductMapper extends EntityMapper <TagForProductDTO, TagForProduct> {

    @Mapping(source = "products.id", target = "productsId")

    @Mapping(source = "tags.id", target = "tagsId")
    TagForProductDTO toDto(TagForProduct tagForProduct); 

    @Mapping(source = "productsId", target = "products")

    @Mapping(source = "tagsId", target = "tags")
    TagForProduct toEntity(TagForProductDTO tagForProductDTO); 
    default TagForProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        TagForProduct tagForProduct = new TagForProduct();
        tagForProduct.setId(id);
        return tagForProduct;
    }
}

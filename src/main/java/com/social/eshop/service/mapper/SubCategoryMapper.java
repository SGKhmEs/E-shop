package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.SubCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SubCategory and its DTO SubCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, })
public interface SubCategoryMapper extends EntityMapper <SubCategoryDTO, SubCategory> {
    @Mapping(source = "category.id", target = "categoryId")
    SubCategoryDTO toDto(SubCategory subCategory); 
    @Mapping(source = "categoryId", target = "category")
    @Mapping(target = "options", ignore = true)
    @Mapping(target = "products", ignore = true)
    SubCategory toEntity(SubCategoryDTO subCategoryDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default SubCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);
        return subCategory;
    }
}

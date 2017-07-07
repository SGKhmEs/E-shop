package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.OptionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Options and its DTO OptionsDTO.
 */
@Mapper(componentModel = "spring", uses = {ValueMapper.class, TypeMapper.class, SubCategoryMapper.class, })
public interface OptionsMapper extends EntityMapper <OptionsDTO, Options> {
    @Mapping(source = "value.id", target = "valueId")
    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "subCategory.id", target = "subCategoryId")
    OptionsDTO toDto(Options options); 
    @Mapping(source = "valueId", target = "value")
    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "subCategoryId", target = "subCategory")
    Options toEntity(OptionsDTO optionsDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Options fromId(Long id) {
        if (id == null) {
            return null;
        }
        Options options = new Options();
        options.setId(id);
        return options;
    }
}

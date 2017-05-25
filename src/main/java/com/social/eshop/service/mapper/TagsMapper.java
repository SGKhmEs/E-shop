package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.TagsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tags and its DTO TagsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TagsMapper extends EntityMapper <TagsDTO, Tags> {
    
    @Mapping(target = "products", ignore = true)
    Tags toEntity(TagsDTO tagsDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Tags fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tags tags = new Tags();
        tags.setId(id);
        return tags;
    }
}

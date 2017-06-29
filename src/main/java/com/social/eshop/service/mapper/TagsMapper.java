package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.TagsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tags and its DTO TagsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TagsMapper extends EntityMapper <TagsDTO, Tags> {
    
    @Mapping(target = "tagForProducts", ignore = true)
    Tags toEntity(TagsDTO tagsDTO); 
    default Tags fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tags tags = new Tags();
        tags.setId(id);
        return tags;
    }
}

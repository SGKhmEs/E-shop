package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.MediaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Media and its DTO MediaDTO.
 */
@Mapper(componentModel = "spring", uses = {CommentsMapper.class, })
public interface MediaMapper extends EntityMapper <MediaDTO, Media> {
    @Mapping(source = "comments.id", target = "commentsId")
    MediaDTO toDto(Media media); 
    @Mapping(source = "commentsId", target = "comments")
    @Mapping(target = "products", ignore = true)
    Media toEntity(MediaDTO mediaDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Media fromId(Long id) {
        if (id == null) {
            return null;
        }
        Media media = new Media();
        media.setId(id);
        return media;
    }
}

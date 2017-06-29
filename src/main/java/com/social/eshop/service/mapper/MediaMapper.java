package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.MediaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Media and its DTO MediaDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductsMapper.class, })
public interface MediaMapper extends EntityMapper <MediaDTO, Media> {

    @Mapping(source = "products.id", target = "productsId")
    MediaDTO toDto(Media media); 

    @Mapping(source = "productsId", target = "products")
    Media toEntity(MediaDTO mediaDTO); 
    default Media fromId(Long id) {
        if (id == null) {
            return null;
        }
        Media media = new Media();
        media.setId(id);
        return media;
    }
}

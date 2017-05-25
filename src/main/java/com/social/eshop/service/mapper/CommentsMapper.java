package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.CommentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comments and its DTO CommentsDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerRoomMapper.class, ProductsMapper.class, })
public interface CommentsMapper extends EntityMapper <CommentsDTO, Comments> {
    @Mapping(source = "customerRoom.id", target = "customerRoomId")
    @Mapping(source = "products.id", target = "productsId")
    CommentsDTO toDto(Comments comments); 
    @Mapping(source = "customerRoomId", target = "customerRoom")
    @Mapping(target = "customers", ignore = true)
    @Mapping(source = "productsId", target = "products")
    Comments toEntity(CommentsDTO commentsDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Comments fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comments comments = new Comments();
        comments.setId(id);
        return comments;
    }
}

package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.CommentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comments and its DTO CommentsDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductsMapper.class, })
public interface CommentsMapper extends EntityMapper <CommentsDTO, Comments> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.personalInfo.firstName", target = "customerName")
    @Mapping(source = "products.id", target = "productsId")

    CommentsDTO toDto(Comments comments);

    @Mapping(source = "customerId", target = "customer")

    @Mapping(source = "productsId", target = "products")
    Comments toEntity(CommentsDTO commentsDTO);
    default Comments fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comments comments = new Comments();
        comments.setId(id);
        return comments;
    }
}

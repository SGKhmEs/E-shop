package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.SeenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Seen and its DTO SeenDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductsMapper.class, })
public interface SeenMapper extends EntityMapper <SeenDTO, Seen> {

    @Mapping(source = "customer.id", target = "customerId")

    @Mapping(source = "products.id", target = "productsId")
    SeenDTO toDto(Seen seen); 

    @Mapping(source = "customerId", target = "customer")

    @Mapping(source = "productsId", target = "products")
    Seen toEntity(SeenDTO seenDTO); 
    default Seen fromId(Long id) {
        if (id == null) {
            return null;
        }
        Seen seen = new Seen();
        seen.setId(id);
        return seen;
    }
}

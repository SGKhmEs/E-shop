package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.SeenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Seen and its DTO SeenDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SeenMapper extends EntityMapper <SeenDTO, Seen> {
    
    @Mapping(target = "products", ignore = true)
    Seen toEntity(SeenDTO seenDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Seen fromId(Long id) {
        if (id == null) {
            return null;
        }
        Seen seen = new Seen();
        seen.setId(id);
        return seen;
    }
}

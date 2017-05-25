package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.TypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Type and its DTO TypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeMapper extends EntityMapper <TypeDTO, Type> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Type fromId(Long id) {
        if (id == null) {
            return null;
        }
        Type type = new Type();
        type.setId(id);
        return type;
    }
}

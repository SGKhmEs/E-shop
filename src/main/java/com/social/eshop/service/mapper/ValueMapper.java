package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Value and its DTO ValueDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ValueMapper extends EntityMapper <ValueDTO, Value> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Value fromId(Long id) {
        if (id == null) {
            return null;
        }
        Value value = new Value();
        value.setId(id);
        return value;
    }
}

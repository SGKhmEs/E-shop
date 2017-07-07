package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ConfirmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Confirm and its DTO ConfirmDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConfirmMapper extends EntityMapper <ConfirmDTO, Confirm> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Confirm fromId(Long id) {
        if (id == null) {
            return null;
        }
        Confirm confirm = new Confirm();
        confirm.setId(id);
        return confirm;
    }
}

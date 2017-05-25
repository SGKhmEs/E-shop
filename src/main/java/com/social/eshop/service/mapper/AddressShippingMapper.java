package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.AddressShippingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AddressShipping and its DTO AddressShippingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressShippingMapper extends EntityMapper <AddressShippingDTO, AddressShipping> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default AddressShipping fromId(Long id) {
        if (id == null) {
            return null;
        }
        AddressShipping addressShipping = new AddressShipping();
        addressShipping.setId(id);
        return addressShipping;
    }
}

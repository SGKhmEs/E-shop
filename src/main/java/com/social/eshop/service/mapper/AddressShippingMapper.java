package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.AddressShippingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AddressShipping and its DTO AddressShippingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressShippingMapper extends EntityMapper <AddressShippingDTO, AddressShipping> {
    
    
    default AddressShipping fromId(Long id) {
        if (id == null) {
            return null;
        }
        AddressShipping addressShipping = new AddressShipping();
        addressShipping.setId(id);
        return addressShipping;
    }
}

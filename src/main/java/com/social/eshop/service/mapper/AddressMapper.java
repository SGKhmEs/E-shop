package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ManagerMapper.class, })
public interface AddressMapper extends EntityMapper <AddressDTO, Address> {

    @Mapping(source = "customer.id", target = "customerId")

    @Mapping(source = "manager.id", target = "managerId")
    AddressDTO toDto(Address address); 

    @Mapping(source = "customerId", target = "customer")

    @Mapping(source = "managerId", target = "manager")
    Address toEntity(AddressDTO addressDTO); 
    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}

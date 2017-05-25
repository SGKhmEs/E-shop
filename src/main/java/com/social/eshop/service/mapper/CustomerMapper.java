package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {LoginOptionsMapper.class, ConfirmMapper.class, CustomerRoomMapper.class, })
public interface CustomerMapper extends EntityMapper <CustomerDTO, Customer> {
    @Mapping(source = "loginOptions.id", target = "loginOptionsId")
    @Mapping(source = "confirm.id", target = "confirmId")
    @Mapping(source = "userRoom.id", target = "userRoomId")
    CustomerDTO toDto(Customer customer); 
    @Mapping(source = "loginOptionsId", target = "loginOptions")
    @Mapping(source = "confirmId", target = "confirm")
    @Mapping(source = "userRoomId", target = "userRoom")
    @Mapping(target = "customers", ignore = true)
    Customer toEntity(CustomerDTO customerDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}

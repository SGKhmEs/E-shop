package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.CustomerAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerAccount and its DTO CustomerAccountDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CustomerMapper.class, })
public interface CustomerAccountMapper extends EntityMapper <CustomerAccountDTO, CustomerAccount> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")

    @Mapping(source = "customer.id", target = "customerId")
    CustomerAccountDTO toDto(CustomerAccount customerAccount); 

    @Mapping(source = "userId", target = "user")

    @Mapping(source = "customerId", target = "customer")
    CustomerAccount toEntity(CustomerAccountDTO customerAccountDTO); 
    default CustomerAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setId(id);
        return customerAccount;
    }
}

package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {LoginOptionsMapper.class, AddressMapper.class, PersonalInformationMapper.class, AvatarMapper.class, CustomerAccountMapper.class, })
public interface CustomerMapper extends EntityMapper <CustomerDTO, Customer> {

    @Mapping(source = "loginOptions.id", target = "loginOptionsId")

    @Mapping(source = "address.id", target = "addressId")

    @Mapping(source = "personalInfo.id", target = "personalInfoId")

    @Mapping(source = "avatar.id", target = "avatarId")

    @Mapping(source = "customerAccount.id", target = "customerAccountId")
    CustomerDTO toDto(Customer customer); 

    @Mapping(source = "loginOptionsId", target = "loginOptions")

    @Mapping(source = "addressId", target = "address")

    @Mapping(source = "personalInfoId", target = "personalInfo")

    @Mapping(source = "avatarId", target = "avatar")
    @Mapping(target = "seens", ignore = true)
    @Mapping(target = "buckets", ignore = true)
    @Mapping(target = "wishLists", ignore = true)

    @Mapping(source = "customerAccountId", target = "customerAccount")
    @Mapping(target = "comments", ignore = true)
    Customer toEntity(CustomerDTO customerDTO); 
    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}

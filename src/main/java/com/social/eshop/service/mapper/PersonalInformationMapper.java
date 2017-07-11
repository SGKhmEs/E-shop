package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.PersonalInformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PersonalInformation and its DTO PersonalInformationDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ManagerMapper.class, })
public interface PersonalInformationMapper extends EntityMapper <PersonalInformationDTO, PersonalInformation> {

    @Mapping(source = "customer.id", target = "customerId")

    @Mapping(source = "manager.id", target = "managerId")
    PersonalInformationDTO toDto(PersonalInformation personalInformation); 

    @Mapping(source = "customerId", target = "customer")

    @Mapping(source = "managerId", target = "manager")
    PersonalInformation toEntity(PersonalInformationDTO personalInformationDTO); 
    default PersonalInformation fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(id);
        return personalInformation;
    }
}

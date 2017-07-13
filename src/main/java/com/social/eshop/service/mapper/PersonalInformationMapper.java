package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.PersonalInformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PersonalInformation and its DTO PersonalInformationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonalInformationMapper extends EntityMapper <PersonalInformationDTO, PersonalInformation> {
    
    
    default PersonalInformation fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(id);
        return personalInformation;
    }
}

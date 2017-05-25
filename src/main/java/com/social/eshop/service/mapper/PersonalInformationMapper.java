package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.PersonalInformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PersonalInformation and its DTO PersonalInformationDTO.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class, AddressShippingMapper.class, })
public interface PersonalInformationMapper extends EntityMapper <PersonalInformationDTO, PersonalInformation> {
    @Mapping(source = "avatar.id", target = "avatarId")
    @Mapping(source = "addressShipping.id", target = "addressShippingId")
    PersonalInformationDTO toDto(PersonalInformation personalInformation); 
    @Mapping(source = "avatarId", target = "avatar")
    @Mapping(source = "addressShippingId", target = "addressShipping")
    PersonalInformation toEntity(PersonalInformationDTO personalInformationDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default PersonalInformation fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(id);
        return personalInformation;
    }
}

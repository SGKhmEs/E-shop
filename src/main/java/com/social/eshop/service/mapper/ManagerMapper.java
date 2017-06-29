package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ManagerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Manager and its DTO ManagerDTO.
 */
@Mapper(componentModel = "spring", uses = {LoginOptionsMapper.class, PersonalInformationMapper.class, AvatarMapper.class, })
public interface ManagerMapper extends EntityMapper <ManagerDTO, Manager> {

    @Mapping(source = "loginOptions.id", target = "loginOptionsId")

    @Mapping(source = "personalInfo.id", target = "personalInfoId")

    @Mapping(source = "avatar.id", target = "avatarId")
    ManagerDTO toDto(Manager manager); 

    @Mapping(source = "loginOptionsId", target = "loginOptions")

    @Mapping(source = "personalInfoId", target = "personalInfo")

    @Mapping(source = "avatarId", target = "avatar")
    @Mapping(target = "manegers", ignore = true)
    Manager toEntity(ManagerDTO managerDTO); 
    default Manager fromId(Long id) {
        if (id == null) {
            return null;
        }
        Manager manager = new Manager();
        manager.setId(id);
        return manager;
    }
}

package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ManagerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Manager and its DTO ManagerDTO.
 */
@Mapper(componentModel = "spring", uses = {LoginOptionsMapper.class, PersonalInformationMapper.class, ManagerAccountMapper.class, AvatarMapper.class, AddressMapper.class, ManagerAccountMapper.class, })
public interface ManagerMapper extends EntityMapper <ManagerDTO, Manager> {

    @Mapping(source = "loginOptions.id", target = "loginOptionsId")

    @Mapping(source = "personalInfo.id", target = "personalInfoId")

    @Mapping(source = "managerAccount.id", target = "managerAccountId")

    @Mapping(source = "avatar.id", target = "avatarId")

    @Mapping(source = "address.id", target = "addressId")
    ManagerDTO toDto(Manager manager);

    @Mapping(source = "loginOptionsId", target = "loginOptions")

    @Mapping(source = "personalInfoId", target = "personalInfo")

    @Mapping(source = "managerAccountId", target = "managerAccount")

    @Mapping(source = "avatarId", target = "avatar")

    @Mapping(source = "addressId", target = "address")

  @Mapping(target = "managers", ignore = true)
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

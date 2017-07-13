package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ManagerAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ManagerAccount and its DTO ManagerAccountDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ManagerMapper.class, })
public interface ManagerAccountMapper extends EntityMapper <ManagerAccountDTO, ManagerAccount> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")

    @Mapping(source = "manager.id", target = "managerId")
    ManagerAccountDTO toDto(ManagerAccount managerAccount); 

    @Mapping(source = "userId", target = "user")

    @Mapping(source = "managerId", target = "manager")
    ManagerAccount toEntity(ManagerAccountDTO managerAccountDTO); 
    default ManagerAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        ManagerAccount managerAccount = new ManagerAccount();
        managerAccount.setId(id);
        return managerAccount;
    }
}

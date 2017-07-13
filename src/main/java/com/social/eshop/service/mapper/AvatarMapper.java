package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.AvatarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Avatar and its DTO AvatarDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ManagerMapper.class, })
public interface AvatarMapper extends EntityMapper <AvatarDTO, Avatar> {

    @Mapping(source = "customer.id", target = "customerId")

    @Mapping(source = "manager.id", target = "managerId")
    AvatarDTO toDto(Avatar avatar); 

    @Mapping(source = "customerId", target = "customer")

    @Mapping(source = "managerId", target = "manager")
    Avatar toEntity(AvatarDTO avatarDTO); 
    default Avatar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avatar avatar = new Avatar();
        avatar.setId(id);
        return avatar;
    }
}

package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.AvatarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Avatar and its DTO AvatarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvatarMapper extends EntityMapper <AvatarDTO, Avatar> {
    
    
    default Avatar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avatar avatar = new Avatar();
        avatar.setId(id);
        return avatar;
    }
}

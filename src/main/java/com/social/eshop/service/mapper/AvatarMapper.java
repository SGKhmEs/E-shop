package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.AvatarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Avatar and its DTO AvatarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvatarMapper extends EntityMapper <AvatarDTO, Avatar> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Avatar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avatar avatar = new Avatar();
        avatar.setId(id);
        return avatar;
    }
}

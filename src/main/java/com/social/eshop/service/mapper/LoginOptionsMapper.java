package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.LoginOptionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LoginOptions and its DTO LoginOptionsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoginOptionsMapper extends EntityMapper <LoginOptionsDTO, LoginOptions> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default LoginOptions fromId(Long id) {
        if (id == null) {
            return null;
        }
        LoginOptions loginOptions = new LoginOptions();
        loginOptions.setId(id);
        return loginOptions;
    }
}

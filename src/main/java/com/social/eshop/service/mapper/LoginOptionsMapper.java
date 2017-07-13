package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.LoginOptionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LoginOptions and its DTO LoginOptionsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoginOptionsMapper extends EntityMapper <LoginOptionsDTO, LoginOptions> {
    
    
    default LoginOptions fromId(Long id) {
        if (id == null) {
            return null;
        }
        LoginOptions loginOptions = new LoginOptions();
        loginOptions.setId(id);
        return loginOptions;
    }
}

package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.OptionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Options and its DTO OptionsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OptionsMapper extends EntityMapper <OptionsDTO, Options> {
    
    
    default Options fromId(Long id) {
        if (id == null) {
            return null;
        }
        Options options = new Options();
        options.setId(id);
        return options;
    }
}

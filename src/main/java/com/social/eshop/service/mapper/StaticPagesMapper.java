package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.StaticPagesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StaticPages and its DTO StaticPagesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StaticPagesMapper extends EntityMapper <StaticPagesDTO, StaticPages> {
    
    
    default StaticPages fromId(Long id) {
        if (id == null) {
            return null;
        }
        StaticPages staticPages = new StaticPages();
        staticPages.setId(id);
        return staticPages;
    }
}

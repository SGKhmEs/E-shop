package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ProducersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Producers and its DTO ProducersDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProducersMapper extends EntityMapper <ProducersDTO, Producers> {
    
    @Mapping(target = "consignments", ignore = true)
    Producers toEntity(ProducersDTO producersDTO); 
    default Producers fromId(Long id) {
        if (id == null) {
            return null;
        }
        Producers producers = new Producers();
        producers.setId(id);
        return producers;
    }
}

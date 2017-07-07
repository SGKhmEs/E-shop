package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.HistoryOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HistoryOrder and its DTO HistoryOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HistoryOrderMapper extends EntityMapper <HistoryOrderDTO, HistoryOrder> {
    
    
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default HistoryOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistoryOrder historyOrder = new HistoryOrder();
        historyOrder.setId(id);
        return historyOrder;
    }
}

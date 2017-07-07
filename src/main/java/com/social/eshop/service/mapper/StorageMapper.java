package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.StorageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Storage and its DTO StorageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StorageMapper extends EntityMapper <StorageDTO, Storage> {
    
    @Mapping(target = "consignments", ignore = true)
    Storage toEntity(StorageDTO storageDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Storage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Storage storage = new Storage();
        storage.setId(id);
        return storage;
    }
}

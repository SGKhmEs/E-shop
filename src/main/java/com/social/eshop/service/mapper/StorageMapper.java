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
    default Storage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Storage storage = new Storage();
        storage.setId(id);
        return storage;
    }
}

package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ConsignmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Consignment and its DTO ConsignmentDTO.
 */
@Mapper(componentModel = "spring", uses = {StorageMapper.class, ProducersMapper.class, })
public interface ConsignmentMapper extends EntityMapper <ConsignmentDTO, Consignment> {

    @Mapping(source = "storage.id", target = "storageId")

    @Mapping(source = "producers.id", target = "producersId")
    ConsignmentDTO toDto(Consignment consignment); 
    @Mapping(target = "products", ignore = true)

    @Mapping(source = "storageId", target = "storage")

    @Mapping(source = "producersId", target = "producers")
    Consignment toEntity(ConsignmentDTO consignmentDTO); 
    default Consignment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Consignment consignment = new Consignment();
        consignment.setId(id);
        return consignment;
    }
}

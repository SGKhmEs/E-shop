package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ConsignmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Consignment and its DTO ConsignmentDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductsMapper.class, StorageMapper.class, ProducersMapper.class, })
public interface ConsignmentMapper extends EntityMapper <ConsignmentDTO, Consignment> {
    @Mapping(source = "products.id", target = "productsId")
    @Mapping(source = "storage.id", target = "storageId")
    @Mapping(source = "producers.id", target = "producersId")
    ConsignmentDTO toDto(Consignment consignment); 
    @Mapping(source = "productsId", target = "products")
    @Mapping(source = "storageId", target = "storage")
    @Mapping(source = "producersId", target = "producers")
    Consignment toEntity(ConsignmentDTO consignmentDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Consignment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Consignment consignment = new Consignment();
        consignment.setId(id);
        return consignment;
    }
}

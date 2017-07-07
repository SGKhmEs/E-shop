package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.BucketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bucket and its DTO BucketDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BucketMapper extends EntityMapper <BucketDTO, Bucket> {
    
    @Mapping(target = "products", ignore = true)
    Bucket toEntity(BucketDTO bucketDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Bucket fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bucket bucket = new Bucket();
        bucket.setId(id);
        return bucket;
    }
}

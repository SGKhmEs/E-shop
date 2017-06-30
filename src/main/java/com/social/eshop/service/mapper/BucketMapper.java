package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.BucketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bucket and its DTO BucketDTO.
 */
@Mapper(componentModel = "spring", uses = {ManagerMapper.class, AddressShippingMapper.class, CustomerMapper.class, ProductInBucketMapper.class, })
public interface BucketMapper extends EntityMapper <BucketDTO, Bucket> {

    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "addressShipping", target = "addressShippingDTO")
    @Mapping(source = "addressShipping.id", target = "addressShippingId")
    @Mapping(source = "customer.personalInfo.firstName", target = "customerName")
    @Mapping(source = "customer.personalInfo.phone", target = "customerPhone")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "productInBuckets", target = "products")
    BucketDTO toDto(Bucket bucket);


    @Mapping(source = "managerId", target = "manager")
    @Mapping(source = "addressShippingId", target = "addressShipping")
    @Mapping(target = "productInBuckets", ignore = true)
    @Mapping(source = "customerId", target = "customer")
    Bucket toEntity(BucketDTO bucketDTO);
    default Bucket fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bucket bucket = new Bucket();
        bucket.setId(id);
        return bucket;
    }
}

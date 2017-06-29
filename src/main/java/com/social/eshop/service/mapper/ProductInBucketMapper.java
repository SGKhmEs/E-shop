package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.ProductInBucketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProductInBucket and its DTO ProductInBucketDTO.
 */
@Mapper(componentModel = "spring", uses = {BucketMapper.class, ProductsMapper.class, })
public interface ProductInBucketMapper extends EntityMapper <ProductInBucketDTO, ProductInBucket> {

    @Mapping(source = "bucket.id", target = "bucketId")

    @Mapping(source = "products.id", target = "productsId")
    ProductInBucketDTO toDto(ProductInBucket productInBucket); 

    @Mapping(source = "bucketId", target = "bucket")

    @Mapping(source = "productsId", target = "products")
    ProductInBucket toEntity(ProductInBucketDTO productInBucketDTO); 
    default ProductInBucket fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductInBucket productInBucket = new ProductInBucket();
        productInBucket.setId(id);
        return productInBucket;
    }
}

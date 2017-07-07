package com.social.eshop.repository;

import com.social.eshop.domain.ProductInBucket;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProductInBucket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductInBucketRepository extends JpaRepository<ProductInBucket,Long> {
    
}

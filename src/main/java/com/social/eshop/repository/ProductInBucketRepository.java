package com.social.eshop.repository;

import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.domain.Products;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the ProductInBucket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductInBucketRepository extends JpaRepository<ProductInBucket,Long> {
    /*
    * Get all products from specific bucket
    * */
    @Query("SELECT p.products FROM ProductInBucket p where bucket_id =?1")
    List<Products> findAllProductsByBucketId(Long id);
}

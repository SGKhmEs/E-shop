package com.social.eshop.repository;

import com.social.eshop.domain.Products;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {
<<<<<<< HEAD
<<<<<<< HEAD
    
=======

>>>>>>> with_entities
=======
    //@Query(" FROM Products inner join ProductInBucket on Products.id = ProductInBucket.id where bucket_id =?1")
    @Query("FROM Products where bucket_id =?1")
    List<Products> findByBucketId(Long id);
>>>>>>> creatingServices
}

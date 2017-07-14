package com.social.eshop.repository;

import com.social.eshop.domain.Bucket;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Bucket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BucketRepository extends JpaRepository<Bucket,Long> {

    @Query("SELECT b FROM Bucket b where customer_id =?1 ")
    List<Bucket> findByCustomerId(Long id);

}

package com.social.eshop.repository;

import com.social.eshop.domain.Bucket;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Bucket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BucketRepository extends JpaRepository<Bucket,Long> {
    
}

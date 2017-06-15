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
    @Query("FROM Bucket where products_id =?1")
    public List<Bucket> findByProductsId(Long id);
}

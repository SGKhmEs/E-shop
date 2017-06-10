package com.social.eshop.service;

import com.social.eshop.domain.Bucket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Bucket.
 */
public interface BucketService {

    /**
     * Save a bucket.
     *
     * @param bucket the entity to save
     * @return the persisted entity
     */
    Bucket save(Bucket bucket);

    /**
     *  Get all the buckets.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Bucket> findAll(Pageable pageable);

    /**
     *  Get the "id" bucket.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Bucket findOne(Long id);

    /**
     *  Delete the "id" bucket.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the bucket corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Bucket> search(String query, Pageable pageable);
}

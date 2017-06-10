package com.social.eshop.service;

import com.social.eshop.domain.ProductInBucket;
import java.util.List;

/**
 * Service Interface for managing ProductInBucket.
 */
public interface ProductInBucketService {

    /**
     * Save a productInBucket.
     *
     * @param productInBucket the entity to save
     * @return the persisted entity
     */
    ProductInBucket save(ProductInBucket productInBucket);

    /**
     *  Get all the productInBuckets.
     *
     *  @return the list of entities
     */
    List<ProductInBucket> findAll();

    /**
     *  Get the "id" productInBucket.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProductInBucket findOne(Long id);

    /**
     *  Delete the "id" productInBucket.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the productInBucket corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<ProductInBucket> search(String query);
}

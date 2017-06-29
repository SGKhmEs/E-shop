package com.social.eshop.service;

import com.social.eshop.service.dto.ProductInBucketDTO;
import java.util.List;

/**
 * Service Interface for managing ProductInBucket.
 */
public interface ProductInBucketService {

    /**
     * Save a productInBucket.
     *
     * @param productInBucketDTO the entity to save
     * @return the persisted entity
     */
    ProductInBucketDTO save(ProductInBucketDTO productInBucketDTO);

    /**
     *  Get all the productInBuckets.
     *
     *  @return the list of entities
     */
    List<ProductInBucketDTO> findAll();

    /**
     *  Get the "id" productInBucket.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProductInBucketDTO findOne(Long id);

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
    List<ProductInBucketDTO> search(String query);
}

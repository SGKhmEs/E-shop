package com.social.eshop.service;

import com.social.eshop.domain.TagForProduct;
import java.util.List;

/**
 * Service Interface for managing TagForProduct.
 */
public interface TagForProductService {

    /**
     * Save a tagForProduct.
     *
     * @param tagForProduct the entity to save
     * @return the persisted entity
     */
    TagForProduct save(TagForProduct tagForProduct);

    /**
     *  Get all the tagForProducts.
     *
     *  @return the list of entities
     */
    List<TagForProduct> findAll();

    /**
     *  Get the "id" tagForProduct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TagForProduct findOne(Long id);

    /**
     *  Delete the "id" tagForProduct.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the tagForProduct corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<TagForProduct> search(String query);
}

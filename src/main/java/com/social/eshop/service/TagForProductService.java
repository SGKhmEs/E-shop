package com.social.eshop.service;

import com.social.eshop.service.dto.TagForProductDTO;
import java.util.List;

/**
 * Service Interface for managing TagForProduct.
 */
public interface TagForProductService {

    /**
     * Save a tagForProduct.
     *
     * @param tagForProductDTO the entity to save
     * @return the persisted entity
     */
    TagForProductDTO save(TagForProductDTO tagForProductDTO);

    /**
     *  Get all the tagForProducts.
     *
     *  @return the list of entities
     */
    List<TagForProductDTO> findAll();

    /**
     *  Get the "id" tagForProduct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TagForProductDTO findOne(Long id);

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
    List<TagForProductDTO> search(String query);
}

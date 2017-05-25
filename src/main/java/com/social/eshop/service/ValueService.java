package com.social.eshop.service;

import com.social.eshop.service.dto.ValueDTO;
import java.util.List;

/**
 * Service Interface for managing Value.
 */
public interface ValueService {

    /**
     * Save a value.
     *
     * @param valueDTO the entity to save
     * @return the persisted entity
     */
    ValueDTO save(ValueDTO valueDTO);

    /**
     *  Get all the values.
     *  
     *  @return the list of entities
     */
    List<ValueDTO> findAll();

    /**
     *  Get the "id" value.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ValueDTO findOne(Long id);

    /**
     *  Delete the "id" value.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the value corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<ValueDTO> search(String query);
}

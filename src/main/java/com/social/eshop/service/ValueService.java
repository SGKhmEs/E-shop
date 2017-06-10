package com.social.eshop.service;

import com.social.eshop.domain.Value;
import java.util.List;

/**
 * Service Interface for managing Value.
 */
public interface ValueService {

    /**
     * Save a value.
     *
     * @param value the entity to save
     * @return the persisted entity
     */
    Value save(Value value);

    /**
     *  Get all the values.
     *
     *  @return the list of entities
     */
    List<Value> findAll();

    /**
     *  Get the "id" value.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Value findOne(Long id);

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
    List<Value> search(String query);
}

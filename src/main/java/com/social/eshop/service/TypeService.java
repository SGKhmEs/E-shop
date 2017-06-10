package com.social.eshop.service;

import com.social.eshop.domain.Type;
import java.util.List;

/**
 * Service Interface for managing Type.
 */
public interface TypeService {

    /**
     * Save a type.
     *
     * @param type the entity to save
     * @return the persisted entity
     */
    Type save(Type type);

    /**
     *  Get all the types.
     *
     *  @return the list of entities
     */
    List<Type> findAll();

    /**
     *  Get the "id" type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Type findOne(Long id);

    /**
     *  Delete the "id" type.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the type corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Type> search(String query);
}

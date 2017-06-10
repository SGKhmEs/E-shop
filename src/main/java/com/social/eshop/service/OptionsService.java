package com.social.eshop.service;

import com.social.eshop.domain.Options;
import java.util.List;

/**
 * Service Interface for managing Options.
 */
public interface OptionsService {

    /**
     * Save a options.
     *
     * @param options the entity to save
     * @return the persisted entity
     */
    Options save(Options options);

    /**
     *  Get all the options.
     *
     *  @return the list of entities
     */
    List<Options> findAll();

    /**
     *  Get the "id" options.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Options findOne(Long id);

    /**
     *  Delete the "id" options.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the options corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Options> search(String query);
}

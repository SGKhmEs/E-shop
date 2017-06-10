package com.social.eshop.service;

import com.social.eshop.domain.Producers;
import java.util.List;

/**
 * Service Interface for managing Producers.
 */
public interface ProducersService {

    /**
     * Save a producers.
     *
     * @param producers the entity to save
     * @return the persisted entity
     */
    Producers save(Producers producers);

    /**
     *  Get all the producers.
     *
     *  @return the list of entities
     */
    List<Producers> findAll();

    /**
     *  Get the "id" producers.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Producers findOne(Long id);

    /**
     *  Delete the "id" producers.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the producers corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Producers> search(String query);
}

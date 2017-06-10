package com.social.eshop.service;

import com.social.eshop.domain.Seen;
import java.util.List;

/**
 * Service Interface for managing Seen.
 */
public interface SeenService {

    /**
     * Save a seen.
     *
     * @param seen the entity to save
     * @return the persisted entity
     */
    Seen save(Seen seen);

    /**
     *  Get all the seens.
     *
     *  @return the list of entities
     */
    List<Seen> findAll();

    /**
     *  Get the "id" seen.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Seen findOne(Long id);

    /**
     *  Delete the "id" seen.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the seen corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Seen> search(String query);
}

package com.social.eshop.service;

import com.social.eshop.domain.StaticPages;
import java.util.List;

/**
 * Service Interface for managing StaticPages.
 */
public interface StaticPagesService {

    /**
     * Save a staticPages.
     *
     * @param staticPages the entity to save
     * @return the persisted entity
     */
    StaticPages save(StaticPages staticPages);

    /**
     *  Get all the staticPages.
     *
     *  @return the list of entities
     */
    List<StaticPages> findAll();

    /**
     *  Get the "id" staticPages.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    StaticPages findOne(Long id);

    /**
     *  Delete the "id" staticPages.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the staticPages corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<StaticPages> search(String query);
}

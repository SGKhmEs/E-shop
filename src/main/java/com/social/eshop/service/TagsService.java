package com.social.eshop.service;

import com.social.eshop.domain.Tags;
import java.util.List;

/**
 * Service Interface for managing Tags.
 */
public interface TagsService {

    /**
     * Save a tags.
     *
     * @param tags the entity to save
     * @return the persisted entity
     */
    Tags save(Tags tags);

    /**
     *  Get all the tags.
     *
     *  @return the list of entities
     */
    List<Tags> findAll();

    /**
     *  Get the "id" tags.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Tags findOne(Long id);

    /**
     *  Delete the "id" tags.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the tags corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Tags> search(String query);
}

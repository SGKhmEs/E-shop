package com.social.eshop.service;

import com.social.eshop.domain.Comments;
import java.util.List;

/**
 * Service Interface for managing Comments.
 */
public interface CommentsService {

    /**
     * Save a comments.
     *
     * @param comments the entity to save
     * @return the persisted entity
     */
    Comments save(Comments comments);

    /**
     *  Get all the comments.
     *
     *  @return the list of entities
     */
    List<Comments> findAll();

    /**
     *  Get the "id" comments.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Comments findOne(Long id);

    /**
     *  Delete the "id" comments.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the comments corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Comments> search(String query);
}

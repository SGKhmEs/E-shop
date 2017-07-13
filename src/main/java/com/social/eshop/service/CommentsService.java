package com.social.eshop.service;

import com.social.eshop.service.dto.CommentsDTO;
import java.util.List;

/**
 * Service Interface for managing Comments.
 */
public interface CommentsService {

    /**
     * Save a comments.
     *
     * @param commentsDTO the entity to save
     * @return the persisted entity
     */
    CommentsDTO save(CommentsDTO commentsDTO);

    /**
     *  Get all the comments.
     *
     *  @return the list of entities
     */
    List<CommentsDTO> findAll();

    /**
     *  Get the "id" comments.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CommentsDTO findOne(Long id);

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
    List<CommentsDTO> search(String query);
}

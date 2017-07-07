package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Comments;
=======
import com.social.eshop.service.dto.CommentsDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Comments.
 */
public interface CommentsService {

    /**
     * Save a comments.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" comments.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Comments findOne(Long id);
=======
    CommentsDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Comments> search(String query);
=======
    List<CommentsDTO> search(String query);
>>>>>>> with_entities
}

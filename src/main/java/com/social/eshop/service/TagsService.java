package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Tags;
=======
import com.social.eshop.service.dto.TagsDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Tags.
 */
public interface TagsService {

    /**
     * Save a tags.
     *
<<<<<<< HEAD
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
=======
     * @param tagsDTO the entity to save
     * @return the persisted entity
     */
    TagsDTO save(TagsDTO tagsDTO);

    /**
     *  Get all the tags.
     *  
     *  @return the list of entities
     */
    List<TagsDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" tags.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Tags findOne(Long id);
=======
    TagsDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Tags> search(String query);
=======
    List<TagsDTO> search(String query);
>>>>>>> with_entities
}

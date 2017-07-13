package com.social.eshop.service;

import com.social.eshop.service.dto.TagsDTO;
import java.util.List;

/**
 * Service Interface for managing Tags.
 */
public interface TagsService {

    /**
     * Save a tags.
     *
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

    /**
     *  Get the "id" tags.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TagsDTO findOne(Long id);

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
    List<TagsDTO> search(String query);
}

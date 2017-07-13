package com.social.eshop.service;

import com.social.eshop.service.dto.StaticPagesDTO;
import java.util.List;

/**
 * Service Interface for managing StaticPages.
 */
public interface StaticPagesService {

    /**
     * Save a staticPages.
     *
     * @param staticPagesDTO the entity to save
     * @return the persisted entity
     */
    StaticPagesDTO save(StaticPagesDTO staticPagesDTO);

    /**
     *  Get all the staticPages.
     *
     *  @return the list of entities
     */
    List<StaticPagesDTO> findAll();

    /**
     *  Get the "id" staticPages.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    StaticPagesDTO findOne(Long id);

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
    List<StaticPagesDTO> search(String query);
}

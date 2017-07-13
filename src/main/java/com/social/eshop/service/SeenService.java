package com.social.eshop.service;

import com.social.eshop.service.dto.SeenDTO;
import java.util.List;

/**
 * Service Interface for managing Seen.
 */
public interface SeenService {

    /**
     * Save a seen.
     *
     * @param seenDTO the entity to save
     * @return the persisted entity
     */
    SeenDTO save(SeenDTO seenDTO);

    /**
     *  Get all the seens.
     *
     *  @return the list of entities
     */
    List<SeenDTO> findAll();

    /**
     *  Get the "id" seen.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SeenDTO findOne(Long id);

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
    List<SeenDTO> search(String query);
}

package com.social.eshop.service;

import com.social.eshop.service.dto.OptionsDTO;
import java.util.List;

/**
 * Service Interface for managing Options.
 */
public interface OptionsService {

    /**
     * Save a options.
     *
     * @param optionsDTO the entity to save
     * @return the persisted entity
     */
    OptionsDTO save(OptionsDTO optionsDTO);

    /**
     *  Get all the options.
     *
     *  @return the list of entities
     */
    List<OptionsDTO> findAll();

    /**
     *  Get the "id" options.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OptionsDTO findOne(Long id);

    /**
     *  Delete the "id" options.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the options corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<OptionsDTO> search(String query);
}

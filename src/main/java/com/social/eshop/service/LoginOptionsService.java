package com.social.eshop.service;

import com.social.eshop.service.dto.LoginOptionsDTO;
import java.util.List;

/**
 * Service Interface for managing LoginOptions.
 */
public interface LoginOptionsService {

    /**
     * Save a loginOptions.
     *
     * @param loginOptionsDTO the entity to save
     * @return the persisted entity
     */
    LoginOptionsDTO save(LoginOptionsDTO loginOptionsDTO);

    /**
     *  Get all the loginOptions.
     *
     *  @return the list of entities
     */
    List<LoginOptionsDTO> findAll();

    /**
     *  Get the "id" loginOptions.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LoginOptionsDTO findOne(Long id);

    /**
     *  Delete the "id" loginOptions.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the loginOptions corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<LoginOptionsDTO> search(String query);
}

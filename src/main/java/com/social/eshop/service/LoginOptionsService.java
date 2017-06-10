package com.social.eshop.service;

import com.social.eshop.domain.LoginOptions;
import java.util.List;

/**
 * Service Interface for managing LoginOptions.
 */
public interface LoginOptionsService {

    /**
     * Save a loginOptions.
     *
     * @param loginOptions the entity to save
     * @return the persisted entity
     */
    LoginOptions save(LoginOptions loginOptions);

    /**
     *  Get all the loginOptions.
     *
     *  @return the list of entities
     */
    List<LoginOptions> findAll();

    /**
     *  Get the "id" loginOptions.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LoginOptions findOne(Long id);

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
    List<LoginOptions> search(String query);
}

package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.LoginOptions;
=======
import com.social.eshop.service.dto.LoginOptionsDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing LoginOptions.
 */
public interface LoginOptionsService {

    /**
     * Save a loginOptions.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" loginOptions.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    LoginOptions findOne(Long id);
=======
    LoginOptionsDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<LoginOptions> search(String query);
=======
    List<LoginOptionsDTO> search(String query);
>>>>>>> with_entities
}

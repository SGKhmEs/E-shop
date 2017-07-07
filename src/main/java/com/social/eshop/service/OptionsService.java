package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Options;
=======
import com.social.eshop.service.dto.OptionsDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Options.
 */
public interface OptionsService {

    /**
     * Save a options.
     *
<<<<<<< HEAD
     * @param options the entity to save
     * @return the persisted entity
     */
    Options save(Options options);

    /**
     *  Get all the options.
     *
     *  @return the list of entities
     */
    List<Options> findAll();
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" options.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Options findOne(Long id);
=======
    OptionsDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Options> search(String query);
=======
    List<OptionsDTO> search(String query);
>>>>>>> with_entities
}

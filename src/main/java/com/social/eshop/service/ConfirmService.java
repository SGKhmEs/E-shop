package com.social.eshop.service;

import com.social.eshop.service.dto.ConfirmDTO;
import java.util.List;

/**
 * Service Interface for managing Confirm.
 */
public interface ConfirmService {

    /**
     * Save a confirm.
     *
     * @param confirmDTO the entity to save
     * @return the persisted entity
     */
    ConfirmDTO save(ConfirmDTO confirmDTO);

    /**
     *  Get all the confirms.
     *  
     *  @return the list of entities
     */
    List<ConfirmDTO> findAll();

    /**
     *  Get the "id" confirm.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ConfirmDTO findOne(Long id);

    /**
     *  Delete the "id" confirm.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the confirm corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<ConfirmDTO> search(String query);
}

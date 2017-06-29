package com.social.eshop.service;

import com.social.eshop.service.dto.ProducersDTO;
import java.util.List;

/**
 * Service Interface for managing Producers.
 */
public interface ProducersService {

    /**
     * Save a producers.
     *
     * @param producersDTO the entity to save
     * @return the persisted entity
     */
    ProducersDTO save(ProducersDTO producersDTO);

    /**
     *  Get all the producers.
     *
     *  @return the list of entities
     */
    List<ProducersDTO> findAll();

    /**
     *  Get the "id" producers.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProducersDTO findOne(Long id);

    /**
     *  Delete the "id" producers.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the producers corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<ProducersDTO> search(String query);
}

package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Producers;
=======
import com.social.eshop.service.dto.ProducersDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Producers.
 */
public interface ProducersService {

    /**
     * Save a producers.
     *
<<<<<<< HEAD
     * @param producers the entity to save
     * @return the persisted entity
     */
    Producers save(Producers producers);

    /**
     *  Get all the producers.
     *
     *  @return the list of entities
     */
    List<Producers> findAll();
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" producers.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Producers findOne(Long id);
=======
    ProducersDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Producers> search(String query);
=======
    List<ProducersDTO> search(String query);
>>>>>>> with_entities
}

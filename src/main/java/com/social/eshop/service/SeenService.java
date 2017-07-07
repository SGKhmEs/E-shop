package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Seen;
=======
import com.social.eshop.service.dto.SeenDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Seen.
 */
public interface SeenService {

    /**
     * Save a seen.
     *
<<<<<<< HEAD
     * @param seen the entity to save
     * @return the persisted entity
     */
    Seen save(Seen seen);

    /**
     *  Get all the seens.
     *
     *  @return the list of entities
     */
    List<Seen> findAll();
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" seen.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Seen findOne(Long id);
=======
    SeenDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Seen> search(String query);
=======
    List<SeenDTO> search(String query);
>>>>>>> with_entities
}

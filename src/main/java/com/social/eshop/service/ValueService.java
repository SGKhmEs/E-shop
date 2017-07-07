package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Value;
=======
import com.social.eshop.service.dto.ValueDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Value.
 */
public interface ValueService {

    /**
     * Save a value.
     *
<<<<<<< HEAD
     * @param value the entity to save
     * @return the persisted entity
     */
    Value save(Value value);

    /**
     *  Get all the values.
     *
     *  @return the list of entities
     */
    List<Value> findAll();
=======
     * @param valueDTO the entity to save
     * @return the persisted entity
     */
    ValueDTO save(ValueDTO valueDTO);

    /**
     *  Get all the values.
     *  
     *  @return the list of entities
     */
    List<ValueDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" value.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Value findOne(Long id);
=======
    ValueDTO findOne(Long id);
>>>>>>> with_entities

    /**
     *  Delete the "id" value.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the value corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
<<<<<<< HEAD
    List<Value> search(String query);
=======
    List<ValueDTO> search(String query);
>>>>>>> with_entities
}

package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Type;
=======
import com.social.eshop.service.dto.TypeDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Type.
 */
public interface TypeService {

    /**
     * Save a type.
     *
<<<<<<< HEAD
     * @param type the entity to save
     * @return the persisted entity
     */
    Type save(Type type);

    /**
     *  Get all the types.
     *
     *  @return the list of entities
     */
    List<Type> findAll();
=======
     * @param typeDTO the entity to save
     * @return the persisted entity
     */
    TypeDTO save(TypeDTO typeDTO);

    /**
     *  Get all the types.
     *  
     *  @return the list of entities
     */
    List<TypeDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Type findOne(Long id);
=======
    TypeDTO findOne(Long id);
>>>>>>> with_entities

    /**
     *  Delete the "id" type.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the type corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
<<<<<<< HEAD
    List<Type> search(String query);
=======
    List<TypeDTO> search(String query);
>>>>>>> with_entities
}

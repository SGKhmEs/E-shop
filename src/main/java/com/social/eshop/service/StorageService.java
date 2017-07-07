package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Storage;
=======
import com.social.eshop.service.dto.StorageDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Storage.
 */
public interface StorageService {

    /**
     * Save a storage.
     *
<<<<<<< HEAD
     * @param storage the entity to save
     * @return the persisted entity
     */
    Storage save(Storage storage);

    /**
     *  Get all the storages.
     *
     *  @return the list of entities
     */
    List<Storage> findAll();
=======
     * @param storageDTO the entity to save
     * @return the persisted entity
     */
    StorageDTO save(StorageDTO storageDTO);

    /**
     *  Get all the storages.
     *  
     *  @return the list of entities
     */
    List<StorageDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" storage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Storage findOne(Long id);
=======
    StorageDTO findOne(Long id);
>>>>>>> with_entities

    /**
     *  Delete the "id" storage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the storage corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
<<<<<<< HEAD
    List<Storage> search(String query);
=======
    List<StorageDTO> search(String query);
>>>>>>> with_entities
}

package com.social.eshop.service;

import com.social.eshop.service.dto.StorageDTO;
import java.util.List;

/**
 * Service Interface for managing Storage.
 */
public interface StorageService {

    /**
     * Save a storage.
     *
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

    /**
     *  Get the "id" storage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    StorageDTO findOne(Long id);

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
    List<StorageDTO> search(String query);
}

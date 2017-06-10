package com.social.eshop.service;

import com.social.eshop.domain.Storage;
import java.util.List;

/**
 * Service Interface for managing Storage.
 */
public interface StorageService {

    /**
     * Save a storage.
     *
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

    /**
     *  Get the "id" storage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Storage findOne(Long id);

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
    List<Storage> search(String query);
}

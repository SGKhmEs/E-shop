package com.social.eshop.service;

import com.social.eshop.domain.Manager;
import java.util.List;

/**
 * Service Interface for managing Manager.
 */
public interface ManagerService {

    /**
     * Save a manager.
     *
     * @param manager the entity to save
     * @return the persisted entity
     */
    Manager save(Manager manager);

    /**
     *  Get all the managers.
     *
     *  @return the list of entities
     */
    List<Manager> findAll();

    /**
     *  Get the "id" manager.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Manager findOne(Long id);

    /**
     *  Delete the "id" manager.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the manager corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Manager> search(String query);
}

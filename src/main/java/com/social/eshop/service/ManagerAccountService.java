package com.social.eshop.service;

import com.social.eshop.service.dto.ManagerAccountDTO;
import java.util.List;

/**
 * Service Interface for managing ManagerAccount.
 */
public interface ManagerAccountService {

    /**
     * Save a managerAccount.
     *
     * @param managerAccountDTO the entity to save
     * @return the persisted entity
     */
    ManagerAccountDTO save(ManagerAccountDTO managerAccountDTO);

    /**
     *  Get all the managerAccounts.
     *
     *  @return the list of entities
     */
    List<ManagerAccountDTO> findAll();

    /**
     *  Get the "id" managerAccount.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ManagerAccountDTO findOne(Long id);

    /**
     *  Delete the "id" managerAccount.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the managerAccount corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<ManagerAccountDTO> search(String query);
}

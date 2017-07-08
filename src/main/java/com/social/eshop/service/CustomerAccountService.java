package com.social.eshop.service;

import com.social.eshop.service.dto.CustomerAccountDTO;
import java.util.List;

/**
 * Service Interface for managing CustomerAccount.
 */
public interface CustomerAccountService {

    /**
     * Save a customerAccount.
     *
     * @param customerAccountDTO the entity to save
     * @return the persisted entity
     */
    CustomerAccountDTO save(CustomerAccountDTO customerAccountDTO);

    /**
     *  Get all the customerAccounts.
     *
     *  @return the list of entities
     */
    List<CustomerAccountDTO> findAll();

    /**
     *  Get the "id" customerAccount.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CustomerAccountDTO findOne(Long id);

    /**
     *  Delete the "id" customerAccount.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerAccount corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<CustomerAccountDTO> search(String query);
}

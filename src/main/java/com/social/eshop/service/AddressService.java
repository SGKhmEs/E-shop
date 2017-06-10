package com.social.eshop.service;

import com.social.eshop.domain.Address;
import java.util.List;

/**
 * Service Interface for managing Address.
 */
public interface AddressService {

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    Address save(Address address);

    /**
     *  Get all the addresses.
     *
     *  @return the list of entities
     */
    List<Address> findAll();

    /**
     *  Get the "id" address.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Address findOne(Long id);

    /**
     *  Delete the "id" address.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the address corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Address> search(String query);
}

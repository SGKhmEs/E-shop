package com.social.eshop.service;

import com.social.eshop.domain.AddressShipping;
import java.util.List;

/**
 * Service Interface for managing AddressShipping.
 */
public interface AddressShippingService {

    /**
     * Save a addressShipping.
     *
     * @param addressShipping the entity to save
     * @return the persisted entity
     */
    AddressShipping save(AddressShipping addressShipping);

    /**
     *  Get all the addressShippings.
     *
     *  @return the list of entities
     */
    List<AddressShipping> findAll();

    /**
     *  Get the "id" addressShipping.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AddressShipping findOne(Long id);

    /**
     *  Delete the "id" addressShipping.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the addressShipping corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<AddressShipping> search(String query);
}

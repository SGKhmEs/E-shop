package com.social.eshop.service;

import com.social.eshop.service.dto.AddressShippingDTO;
import java.util.List;

/**
 * Service Interface for managing AddressShipping.
 */
public interface AddressShippingService {

    /**
     * Save a addressShipping.
     *
     * @param addressShippingDTO the entity to save
     * @return the persisted entity
     */
    AddressShippingDTO save(AddressShippingDTO addressShippingDTO);

    /**
     *  Get all the addressShippings.
     *
     *  @return the list of entities
     */
    List<AddressShippingDTO> findAll();

    /**
     *  Get the "id" addressShipping.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AddressShippingDTO findOne(Long id);

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
    List<AddressShippingDTO> search(String query);
}

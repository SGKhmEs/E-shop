package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.AddressShipping;
=======
import com.social.eshop.service.dto.AddressShippingDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing AddressShipping.
 */
public interface AddressShippingService {

    /**
     * Save a addressShipping.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" addressShipping.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    AddressShipping findOne(Long id);
=======
    AddressShippingDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<AddressShipping> search(String query);
=======
    List<AddressShippingDTO> search(String query);
>>>>>>> with_entities
}

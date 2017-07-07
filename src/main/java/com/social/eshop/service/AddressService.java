package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Address;
=======
import com.social.eshop.service.dto.AddressDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Address.
 */
public interface AddressService {

    /**
     * Save a address.
     *
<<<<<<< HEAD
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
=======
     * @param addressDTO the entity to save
     * @return the persisted entity
     */
    AddressDTO save(AddressDTO addressDTO);

    /**
     *  Get all the addresses.
     *  
     *  @return the list of entities
     */
    List<AddressDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" address.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Address findOne(Long id);
=======
    AddressDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Address> search(String query);
=======
    List<AddressDTO> search(String query);
>>>>>>> with_entities
}

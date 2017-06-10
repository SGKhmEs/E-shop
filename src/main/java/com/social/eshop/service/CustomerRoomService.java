package com.social.eshop.service;

import com.social.eshop.domain.CustomerRoom;
import java.util.List;

/**
 * Service Interface for managing CustomerRoom.
 */
public interface CustomerRoomService {

    /**
     * Save a customerRoom.
     *
     * @param customerRoom the entity to save
     * @return the persisted entity
     */
    CustomerRoom save(CustomerRoom customerRoom);

    /**
     *  Get all the customerRooms.
     *
     *  @return the list of entities
     */
    List<CustomerRoom> findAll();

    /**
     *  Get the "id" customerRoom.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CustomerRoom findOne(Long id);

    /**
     *  Delete the "id" customerRoom.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customerRoom corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<CustomerRoom> search(String query);
}

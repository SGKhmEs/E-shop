package com.social.eshop.service;

import com.social.eshop.service.dto.CustomerRoomDTO;
import java.util.List;

/**
 * Service Interface for managing CustomerRoom.
 */
public interface CustomerRoomService {

    /**
     * Save a customerRoom.
     *
     * @param customerRoomDTO the entity to save
     * @return the persisted entity
     */
    CustomerRoomDTO save(CustomerRoomDTO customerRoomDTO);

    /**
     *  Get all the customerRooms.
     *  
     *  @return the list of entities
     */
    List<CustomerRoomDTO> findAll();

    /**
     *  Get the "id" customerRoom.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CustomerRoomDTO findOne(Long id);

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
    List<CustomerRoomDTO> search(String query);
}

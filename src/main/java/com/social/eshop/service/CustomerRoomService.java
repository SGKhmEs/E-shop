package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.CustomerRoom;
=======
import com.social.eshop.service.dto.CustomerRoomDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing CustomerRoom.
 */
public interface CustomerRoomService {

    /**
     * Save a customerRoom.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" customerRoom.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    CustomerRoom findOne(Long id);
=======
    CustomerRoomDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<CustomerRoom> search(String query);
=======
    List<CustomerRoomDTO> search(String query);
>>>>>>> with_entities
}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.CustomerRoom;
import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.CustomerRoomDTO;
>>>>>>> with_entities
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CustomerRoom.
 */
@RestController
@RequestMapping("/api")
public class CustomerRoomResource {

    private final Logger log = LoggerFactory.getLogger(CustomerRoomResource.class);

    private static final String ENTITY_NAME = "customerRoom";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final CustomerRoomService customerRoomService;

    public CustomerRoomResource(CustomerRoomService customerRoomService) {
        this.customerRoomService = customerRoomService;
    }

    /**
     * POST  /customer-rooms : Create a new customerRoom.
     *
<<<<<<< HEAD
     * @param customerRoom the customerRoom to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerRoom, or with status 400 (Bad Request) if the customerRoom has already an ID
=======
     * @param customerRoomDTO the customerRoomDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerRoomDTO, or with status 400 (Bad Request) if the customerRoom has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-rooms")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<CustomerRoom> createCustomerRoom(@RequestBody CustomerRoom customerRoom) throws URISyntaxException {
        log.debug("REST request to save CustomerRoom : {}", customerRoom);
        if (customerRoom.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customerRoom cannot already have an ID")).body(null);
        }
        CustomerRoom result = customerRoomService.save(customerRoom);
=======
    public ResponseEntity<CustomerRoomDTO> createCustomerRoom(@RequestBody CustomerRoomDTO customerRoomDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerRoom : {}", customerRoomDTO);
        if (customerRoomDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customerRoom cannot already have an ID")).body(null);
        }
        CustomerRoomDTO result = customerRoomService.save(customerRoomDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/customer-rooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-rooms : Updates an existing customerRoom.
     *
<<<<<<< HEAD
     * @param customerRoom the customerRoom to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerRoom,
     * or with status 400 (Bad Request) if the customerRoom is not valid,
     * or with status 500 (Internal Server Error) if the customerRoom couldn't be updated
=======
     * @param customerRoomDTO the customerRoomDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerRoomDTO,
     * or with status 400 (Bad Request) if the customerRoomDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerRoomDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-rooms")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<CustomerRoom> updateCustomerRoom(@RequestBody CustomerRoom customerRoom) throws URISyntaxException {
        log.debug("REST request to update CustomerRoom : {}", customerRoom);
        if (customerRoom.getId() == null) {
            return createCustomerRoom(customerRoom);
        }
        CustomerRoom result = customerRoomService.save(customerRoom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerRoom.getId().toString()))
=======
    public ResponseEntity<CustomerRoomDTO> updateCustomerRoom(@RequestBody CustomerRoomDTO customerRoomDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerRoom : {}", customerRoomDTO);
        if (customerRoomDTO.getId() == null) {
            return createCustomerRoom(customerRoomDTO);
        }
        CustomerRoomDTO result = customerRoomService.save(customerRoomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerRoomDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /customer-rooms : get all the customerRooms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customerRooms in body
     */
    @GetMapping("/customer-rooms")
    @Timed
<<<<<<< HEAD
    public List<CustomerRoom> getAllCustomerRooms() {
=======
    public List<CustomerRoomDTO> getAllCustomerRooms() {
>>>>>>> with_entities
        log.debug("REST request to get all CustomerRooms");
        return customerRoomService.findAll();
    }

    /**
     * GET  /customer-rooms/:id : get the "id" customerRoom.
     *
<<<<<<< HEAD
     * @param id the id of the customerRoom to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerRoom, or with status 404 (Not Found)
     */
    @GetMapping("/customer-rooms/{id}")
    @Timed
    public ResponseEntity<CustomerRoom> getCustomerRoom(@PathVariable Long id) {
        log.debug("REST request to get CustomerRoom : {}", id);
        CustomerRoom customerRoom = customerRoomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerRoom));
=======
     * @param id the id of the customerRoomDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerRoomDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-rooms/{id}")
    @Timed
    public ResponseEntity<CustomerRoomDTO> getCustomerRoom(@PathVariable Long id) {
        log.debug("REST request to get CustomerRoom : {}", id);
        CustomerRoomDTO customerRoomDTO = customerRoomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerRoomDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /customer-rooms/:id : delete the "id" customerRoom.
     *
<<<<<<< HEAD
     * @param id the id of the customerRoom to delete
=======
     * @param id the id of the customerRoomDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-rooms/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerRoom(@PathVariable Long id) {
        log.debug("REST request to delete CustomerRoom : {}", id);
        customerRoomService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-rooms?query=:query : search for the customerRoom corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the customerRoom search
=======
     * @param query the query of the customerRoom search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/customer-rooms")
    @Timed
<<<<<<< HEAD
    public List<CustomerRoom> searchCustomerRooms(@RequestParam String query) {
=======
    public List<CustomerRoomDTO> searchCustomerRooms(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search CustomerRooms for query {}", query);
        return customerRoomService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

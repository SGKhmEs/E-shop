package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.CustomerRoom;
import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.web.rest.util.HeaderUtil;
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

    private final CustomerRoomService customerRoomService;

    public CustomerRoomResource(CustomerRoomService customerRoomService) {
        this.customerRoomService = customerRoomService;
    }

    /**
     * POST  /customer-rooms : Create a new customerRoom.
     *
     * @param customerRoom the customerRoom to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerRoom, or with status 400 (Bad Request) if the customerRoom has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-rooms")
    @Timed
    public ResponseEntity<CustomerRoom> createCustomerRoom(@RequestBody CustomerRoom customerRoom) throws URISyntaxException {
        log.debug("REST request to save CustomerRoom : {}", customerRoom);
        if (customerRoom.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customerRoom cannot already have an ID")).body(null);
        }
        CustomerRoom result = customerRoomService.save(customerRoom);
        return ResponseEntity.created(new URI("/api/customer-rooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-rooms : Updates an existing customerRoom.
     *
     * @param customerRoom the customerRoom to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerRoom,
     * or with status 400 (Bad Request) if the customerRoom is not valid,
     * or with status 500 (Internal Server Error) if the customerRoom couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-rooms")
    @Timed
    public ResponseEntity<CustomerRoom> updateCustomerRoom(@RequestBody CustomerRoom customerRoom) throws URISyntaxException {
        log.debug("REST request to update CustomerRoom : {}", customerRoom);
        if (customerRoom.getId() == null) {
            return createCustomerRoom(customerRoom);
        }
        CustomerRoom result = customerRoomService.save(customerRoom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerRoom.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-rooms : get all the customerRooms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customerRooms in body
     */
    @GetMapping("/customer-rooms")
    @Timed
    public List<CustomerRoom> getAllCustomerRooms() {
        log.debug("REST request to get all CustomerRooms");
        return customerRoomService.findAll();
    }

    /**
     * GET  /customer-rooms/:id : get the "id" customerRoom.
     *
     * @param id the id of the customerRoom to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerRoom, or with status 404 (Not Found)
     */
    @GetMapping("/customer-rooms/{id}")
    @Timed
    public ResponseEntity<CustomerRoom> getCustomerRoom(@PathVariable Long id) {
        log.debug("REST request to get CustomerRoom : {}", id);
        CustomerRoom customerRoom = customerRoomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerRoom));
    }

    /**
     * DELETE  /customer-rooms/:id : delete the "id" customerRoom.
     *
     * @param id the id of the customerRoom to delete
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
     * @param query the query of the customerRoom search
     * @return the result of the search
     */
    @GetMapping("/_search/customer-rooms")
    @Timed
    public List<CustomerRoom> searchCustomerRooms(@RequestParam String query) {
        log.debug("REST request to search CustomerRooms for query {}", query);
        return customerRoomService.search(query);
    }

}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.CustomerRoomDTO;
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
     * @param customerRoomDTO the customerRoomDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerRoomDTO, or with status 400 (Bad Request) if the customerRoom has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-rooms")
    @Timed
    public ResponseEntity<CustomerRoomDTO> createCustomerRoom(@RequestBody CustomerRoomDTO customerRoomDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerRoom : {}", customerRoomDTO);
        if (customerRoomDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customerRoom cannot already have an ID")).body(null);
        }
        CustomerRoomDTO result = customerRoomService.save(customerRoomDTO);
        return ResponseEntity.created(new URI("/api/customer-rooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-rooms : Updates an existing customerRoom.
     *
     * @param customerRoomDTO the customerRoomDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerRoomDTO,
     * or with status 400 (Bad Request) if the customerRoomDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerRoomDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-rooms")
    @Timed
    public ResponseEntity<CustomerRoomDTO> updateCustomerRoom(@RequestBody CustomerRoomDTO customerRoomDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerRoom : {}", customerRoomDTO);
        if (customerRoomDTO.getId() == null) {
            return createCustomerRoom(customerRoomDTO);
        }
        CustomerRoomDTO result = customerRoomService.save(customerRoomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerRoomDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-rooms : get all the customerRooms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customerRooms in body
     */
    @GetMapping("/customer-rooms")
    @Timed
    public List<CustomerRoomDTO> getAllCustomerRooms() {
        log.debug("REST request to get all CustomerRooms");
        return customerRoomService.findAll();
    }

    /**
     * GET  /customer-rooms/:id : get the "id" customerRoom.
     *
     * @param id the id of the customerRoomDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerRoomDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-rooms/{id}")
    @Timed
    public ResponseEntity<CustomerRoomDTO> getCustomerRoom(@PathVariable Long id) {
        log.debug("REST request to get CustomerRoom : {}", id);
        CustomerRoomDTO customerRoomDTO = customerRoomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerRoomDTO));
    }

    /**
     * DELETE  /customer-rooms/:id : delete the "id" customerRoom.
     *
     * @param id the id of the customerRoomDTO to delete
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
    public List<CustomerRoomDTO> searchCustomerRooms(@RequestParam String query) {
        log.debug("REST request to search CustomerRooms for query {}", query);
        return customerRoomService.search(query);
    }


}

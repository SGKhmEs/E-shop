package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.AddressShippingService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.AddressShippingDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing AddressShipping.
 */
@RestController
@RequestMapping("/api")
public class AddressShippingResource {

    private final Logger log = LoggerFactory.getLogger(AddressShippingResource.class);

    private static final String ENTITY_NAME = "addressShipping";

    private final AddressShippingService addressShippingService;

    public AddressShippingResource(AddressShippingService addressShippingService) {
        this.addressShippingService = addressShippingService;
    }

    /**
     * POST  /address-shippings : Create a new addressShipping.
     *
     * @param addressShippingDTO the addressShippingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new addressShippingDTO, or with status 400 (Bad Request) if the addressShipping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/address-shippings")
    @Timed
    public ResponseEntity<AddressShippingDTO> createAddressShipping(@Valid @RequestBody AddressShippingDTO addressShippingDTO) throws URISyntaxException {
        log.debug("REST request to save AddressShipping : {}", addressShippingDTO);
        if (addressShippingDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new addressShipping cannot already have an ID")).body(null);
        }
        AddressShippingDTO result = addressShippingService.save(addressShippingDTO);
        return ResponseEntity.created(new URI("/api/address-shippings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /address-shippings : Updates an existing addressShipping.
     *
     * @param addressShippingDTO the addressShippingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated addressShippingDTO,
     * or with status 400 (Bad Request) if the addressShippingDTO is not valid,
     * or with status 500 (Internal Server Error) if the addressShippingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/address-shippings")
    @Timed
    public ResponseEntity<AddressShippingDTO> updateAddressShipping(@Valid @RequestBody AddressShippingDTO addressShippingDTO) throws URISyntaxException {
        log.debug("REST request to update AddressShipping : {}", addressShippingDTO);
        if (addressShippingDTO.getId() == null) {
            return createAddressShipping(addressShippingDTO);
        }
        AddressShippingDTO result = addressShippingService.save(addressShippingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, addressShippingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /address-shippings : get all the addressShippings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of addressShippings in body
     */
    @GetMapping("/address-shippings")
    @Timed
    public List<AddressShippingDTO> getAllAddressShippings() {
        log.debug("REST request to get all AddressShippings");
        return addressShippingService.findAll();
    }

    /**
     * GET  /address-shippings/:id : get the "id" addressShipping.
     *
     * @param id the id of the addressShippingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the addressShippingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/address-shippings/{id}")
    @Timed
    public ResponseEntity<AddressShippingDTO> getAddressShipping(@PathVariable Long id) {
        log.debug("REST request to get AddressShipping : {}", id);
        AddressShippingDTO addressShippingDTO = addressShippingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(addressShippingDTO));
    }

    /**
     * DELETE  /address-shippings/:id : delete the "id" addressShipping.
     *
     * @param id the id of the addressShippingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/address-shippings/{id}")
    @Timed
    public ResponseEntity<Void> deleteAddressShipping(@PathVariable Long id) {
        log.debug("REST request to delete AddressShipping : {}", id);
        addressShippingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/address-shippings?query=:query : search for the addressShipping corresponding
     * to the query.
     *
     * @param query the query of the addressShipping search
     * @return the result of the search
     */
    @GetMapping("/_search/address-shippings")
    @Timed
    public List<AddressShippingDTO> searchAddressShippings(@RequestParam String query) {
        log.debug("REST request to search AddressShippings for query {}", query);
        return addressShippingService.search(query);
    }

}

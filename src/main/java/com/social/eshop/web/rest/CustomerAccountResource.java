package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.CustomerAccountService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.CustomerAccountDTO;
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
 * REST controller for managing CustomerAccount.
 */
@RestController
@RequestMapping("/api")
public class CustomerAccountResource {

    private final Logger log = LoggerFactory.getLogger(CustomerAccountResource.class);

    private static final String ENTITY_NAME = "customerAccount";

    private final CustomerAccountService customerAccountService;

    public CustomerAccountResource(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    /**
     * POST  /customer-accounts : Create a new customerAccount.
     *
     * @param customerAccountDTO the customerAccountDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerAccountDTO, or with status 400 (Bad Request) if the customerAccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-accounts")
    @Timed
    public ResponseEntity<CustomerAccountDTO> createCustomerAccount(@RequestBody CustomerAccountDTO customerAccountDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerAccount : {}", customerAccountDTO);
        if (customerAccountDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customerAccount cannot already have an ID")).body(null);
        }
        CustomerAccountDTO result = customerAccountService.save(customerAccountDTO);
        return ResponseEntity.created(new URI("/api/customer-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-accounts : Updates an existing customerAccount.
     *
     * @param customerAccountDTO the customerAccountDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerAccountDTO,
     * or with status 400 (Bad Request) if the customerAccountDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerAccountDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-accounts")
    @Timed
    public ResponseEntity<CustomerAccountDTO> updateCustomerAccount(@RequestBody CustomerAccountDTO customerAccountDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerAccount : {}", customerAccountDTO);
        if (customerAccountDTO.getId() == null) {
            return createCustomerAccount(customerAccountDTO);
        }
        CustomerAccountDTO result = customerAccountService.save(customerAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-accounts : get all the customerAccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customerAccounts in body
     */
    @GetMapping("/customer-accounts")
    @Timed
    public List<CustomerAccountDTO> getAllCustomerAccounts() {
        log.debug("REST request to get all CustomerAccounts");
        return customerAccountService.findAll();
    }

    /**
     * GET  /customer-accounts/:id : get the "id" customerAccount.
     *
     * @param id the id of the customerAccountDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerAccountDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-accounts/{id}")
    @Timed
    public ResponseEntity<CustomerAccountDTO> getCustomerAccount(@PathVariable Long id) {
        log.debug("REST request to get CustomerAccount : {}", id);
        CustomerAccountDTO customerAccountDTO = customerAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerAccountDTO));
    }

    /**
     * DELETE  /customer-accounts/:id : delete the "id" customerAccount.
     *
     * @param id the id of the customerAccountDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-accounts/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerAccount(@PathVariable Long id) {
        log.debug("REST request to delete CustomerAccount : {}", id);
        customerAccountService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customer-accounts?query=:query : search for the customerAccount corresponding
     * to the query.
     *
     * @param query the query of the customerAccount search
     * @return the result of the search
     */
    @GetMapping("/_search/customer-accounts")
    @Timed
    public List<CustomerAccountDTO> searchCustomerAccounts(@RequestParam String query) {
        log.debug("REST request to search CustomerAccounts for query {}", query);
        return customerAccountService.search(query);
    }

}

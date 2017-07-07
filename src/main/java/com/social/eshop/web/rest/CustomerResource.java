package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.Customer;
import com.social.eshop.service.CustomerService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
=======
import com.social.eshop.service.CustomerService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.CustomerDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> with_entities
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * POST  /customers : Create a new customer.
     *
<<<<<<< HEAD
     * @param customer the customer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customer, or with status 400 (Bad Request) if the customer has already an ID
=======
     * @param customerDTO the customerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerDTO, or with status 400 (Bad Request) if the customer has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customers")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customer);
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customer cannot already have an ID")).body(null);
        }
        Customer result = customerService.save(customer);
=======
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customerDTO);
        if (customerDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new customer cannot already have an ID")).body(null);
        }
        CustomerDTO result = customerService.save(customerDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customers : Updates an existing customer.
     *
<<<<<<< HEAD
     * @param customer the customer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customer,
     * or with status 400 (Bad Request) if the customer is not valid,
     * or with status 500 (Internal Server Error) if the customer couldn't be updated
=======
     * @param customerDTO the customerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerDTO,
     * or with status 400 (Bad Request) if the customerDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customers")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customer);
        if (customer.getId() == null) {
            return createCustomer(customer);
        }
        Customer result = customerService.save(customer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customer.getId().toString()))
=======
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customerDTO);
        if (customerDTO.getId() == null) {
            return createCustomer(customerDTO);
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /customers : get all the customers.
     *
<<<<<<< HEAD
     * @param pageable the pagination information
=======
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     */
    @GetMapping("/customers")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<List<Customer>> getAllCustomers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Customers");
        Page<Customer> page = customerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
=======
    public List<CustomerDTO> getAllCustomers() {
        log.debug("REST request to get all Customers");
        return customerService.findAll();
>>>>>>> with_entities
    }

    /**
     * GET  /customers/:id : get the "id" customer.
     *
<<<<<<< HEAD
     * @param id the id of the customer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customer, or with status 404 (Not Found)
     */
    @GetMapping("/customers/{id}")
    @Timed
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Customer customer = customerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customer));
=======
     * @param id the id of the customerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customers/{id}")
    @Timed
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        CustomerDTO customerDTO = customerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /customers/:id : delete the "id" customer.
     *
<<<<<<< HEAD
     * @param id the id of the customer to delete
=======
     * @param id the id of the customerDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customers/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        customerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/customers?query=:query : search for the customer corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the customer search
     * @param pageable the pagination information
=======
     * @param query the query of the customer search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/customers")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Customers for query {}", query);
        Page<Customer> page = customerService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/customers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

=======
    public List<CustomerDTO> searchCustomers(@RequestParam String query) {
        log.debug("REST request to search Customers for query {}", query);
        return customerService.search(query);
    }


>>>>>>> with_entities
}

package com.social.eshop.service.impl;

import com.social.eshop.service.CustomerService;
import com.social.eshop.domain.Customer;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.repository.search.CustomerSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.CustomerDTO;
import com.social.eshop.service.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
>>>>>>> with_entities

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
<<<<<<< HEAD

    private final CustomerRepository customerRepository;

    private final CustomerSearchRepository customerSearchRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerSearchRepository customerSearchRepository) {
        this.customerRepository = customerRepository;
=======
    
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CustomerSearchRepository customerSearchRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerSearchRepository customerSearchRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
>>>>>>> with_entities
        this.customerSearchRepository = customerSearchRepository;
    }

    /**
     * Save a customer.
     *
<<<<<<< HEAD
     * @param customer the entity to save
     * @return the persisted entity
     */
    @Override
    public Customer save(Customer customer) {
        log.debug("Request to save Customer : {}", customer);
        Customer result = customerRepository.save(customer);
        customerSearchRepository.save(result);
=======
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        CustomerDTO result = customerMapper.toDto(customer);
        customerSearchRepository.save(customer);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the customers.
<<<<<<< HEAD
     *
     *  @param pageable the pagination information
=======
     *  
>>>>>>> with_entities
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Customer> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable);
=======
    public List<CustomerDTO> findAll() {
        log.debug("Request to get all Customers");
        List<CustomerDTO> result = customerRepository.findAll().stream()
            .map(customerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one customer by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Customer findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findOne(id);
=======
    public CustomerDTO findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        Customer customer = customerRepository.findOne(id);
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        return customerDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  customer by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.delete(id);
        customerSearchRepository.delete(id);
    }

    /**
     * Search for the customer corresponding to the query.
     *
     *  @param query the query of the search
<<<<<<< HEAD
     *  @param pageable the pagination information
=======
>>>>>>> with_entities
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Customer> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Customers for query {}", query);
        Page<Customer> result = customerSearchRepository.search(queryStringQuery(query), pageable);
        return result;
=======
    public List<CustomerDTO> search(String query) {
        log.debug("Request to search Customers for query {}", query);
        return StreamSupport
            .stream(customerSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerMapper::toDto)
            .collect(Collectors.toList());
>>>>>>> with_entities
    }
}

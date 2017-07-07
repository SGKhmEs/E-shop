package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import com.social.eshop.service.dto.CustomerDTO;
import java.util.List;
>>>>>>> with_entities

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService {

    /**
     * Save a customer.
     *
<<<<<<< HEAD
     * @param customer the entity to save
     * @return the persisted entity
     */
    Customer save(Customer customer);

    /**
     *  Get all the customers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Customer> findAll(Pageable pageable);
=======
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    CustomerDTO save(CustomerDTO customerDTO);

    /**
     *  Get all the customers.
     *  
     *  @return the list of entities
     */
    List<CustomerDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" customer.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Customer findOne(Long id);
=======
    CustomerDTO findOne(Long id);
>>>>>>> with_entities

    /**
     *  Delete the "id" customer.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customer corresponding to the query.
     *
     *  @param query the query of the search
     *  
<<<<<<< HEAD
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Customer> search(String query, Pageable pageable);
=======
     *  @return the list of entities
     */
    List<CustomerDTO> search(String query);
>>>>>>> with_entities
}

package com.social.eshop.service;

import com.social.eshop.domain.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Products.
 */
public interface ProductsService {

    /**
     * Save a products.
     *
     * @param products the entity to save
     * @return the persisted entity
     */
    Products save(Products products);

    /**
     *  Get all the products.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Products> findAll(Pageable pageable);

    /**
     *  Get the "id" products.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Products findOne(Long id);

    /**
     *  Delete the "id" products.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the products corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Products> search(String query, Pageable pageable);
}

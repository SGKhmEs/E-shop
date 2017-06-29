package com.social.eshop.service;

import com.social.eshop.service.dto.ProductsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Products.
 */
public interface ProductsService {

    /**
     * Save a products.
     *
     * @param productsDTO the entity to save
     * @return the persisted entity
     */
    ProductsDTO save(ProductsDTO productsDTO);

    /**
     *  Get all the products.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProductsDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" products.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProductsDTO findOne(Long id);

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
    Page<ProductsDTO> search(String query, Pageable pageable);
}

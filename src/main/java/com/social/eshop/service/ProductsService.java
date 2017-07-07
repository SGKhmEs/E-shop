package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Products;
=======
import com.social.eshop.service.dto.ProductsDTO;
>>>>>>> with_entities
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Products.
 */
public interface ProductsService {

    /**
     * Save a products.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" products.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Products findOne(Long id);
=======
    ProductsDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    Page<Products> search(String query, Pageable pageable);
=======
    Page<ProductsDTO> search(String query, Pageable pageable);
>>>>>>> with_entities
}

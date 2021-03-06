package com.social.eshop.service;

import com.social.eshop.service.dto.ProductsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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


    /**
     *  Get all the products from specific bucket.
     *
     *  @param id the id of the bucket
     *  @return the list of entities
     */
    List<ProductsDTO> findAllProductsInBucket(Long id) ;

    /**
     *  Get all the products with specific tag.
     *
     *  @param id the id of the tag
     *  @return the list of entities
     */
    List<ProductsDTO> findAllProductsWithTag(Long id);

    /**
     *  Get all the products from specific category.
     *
     *  @param id the id of the category
     *  @return the list of entities
     */
    List<ProductsDTO> findAllProductsInCategory(Long id);
}

package com.social.eshop.service.impl;

import com.social.eshop.service.ProductsService;
import com.social.eshop.domain.Products;
import com.social.eshop.repository.ProductsRepository;
import com.social.eshop.repository.search.ProductsSearchRepository;
<<<<<<< HEAD
=======
import com.social.eshop.service.dto.ProductsDTO;
import com.social.eshop.service.mapper.ProductsMapper;
>>>>>>> with_entities
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> with_entities

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Products.
 */
@Service
@Transactional
public class ProductsServiceImpl implements ProductsService{

    private final Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);
<<<<<<< HEAD

    private final ProductsRepository productsRepository;

    private final ProductsSearchRepository productsSearchRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductsSearchRepository productsSearchRepository) {
        this.productsRepository = productsRepository;
=======
    
    private final ProductsRepository productsRepository;

    private final ProductsMapper productsMapper;

    private final ProductsSearchRepository productsSearchRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductsMapper productsMapper, ProductsSearchRepository productsSearchRepository) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
>>>>>>> with_entities
        this.productsSearchRepository = productsSearchRepository;
    }

    /**
     * Save a products.
     *
<<<<<<< HEAD
     * @param products the entity to save
     * @return the persisted entity
     */
    @Override
    public Products save(Products products) {
        log.debug("Request to save Products : {}", products);
        Products result = productsRepository.save(products);
        productsSearchRepository.save(result);
=======
     * @param productsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductsDTO save(ProductsDTO productsDTO) {
        log.debug("Request to save Products : {}", productsDTO);
        Products products = productsMapper.toEntity(productsDTO);
        products = productsRepository.save(products);
        ProductsDTO result = productsMapper.toDto(products);
        productsSearchRepository.save(products);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the products.
<<<<<<< HEAD
     *
=======
     *  
>>>>>>> with_entities
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Products> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productsRepository.findAll(pageable);
=======
    public Page<ProductsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        Page<Products> result = productsRepository.findAll(pageable);
        return result.map(products -> productsMapper.toDto(products));
>>>>>>> with_entities
    }

    /**
     *  Get one products by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Products findOne(Long id) {
        log.debug("Request to get Products : {}", id);
        return productsRepository.findOne(id);
=======
    public ProductsDTO findOne(Long id) {
        log.debug("Request to get Products : {}", id);
        Products products = productsRepository.findOne(id);
        ProductsDTO productsDTO = productsMapper.toDto(products);
        return productsDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  products by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Products : {}", id);
        productsRepository.delete(id);
        productsSearchRepository.delete(id);
    }

    /**
     * Search for the products corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Products> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Products for query {}", query);
        Page<Products> result = productsSearchRepository.search(queryStringQuery(query), pageable);
        return result;
=======
    public Page<ProductsDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Products for query {}", query);
        Page<Products> result = productsSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(products -> productsMapper.toDto(products));
>>>>>>> with_entities
    }
}

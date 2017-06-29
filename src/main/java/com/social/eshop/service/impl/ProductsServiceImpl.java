package com.social.eshop.service.impl;

import com.social.eshop.service.ProductsService;
import com.social.eshop.domain.Products;
import com.social.eshop.repository.ProductsRepository;
import com.social.eshop.repository.search.ProductsSearchRepository;
import com.social.eshop.service.dto.ProductsDTO;
import com.social.eshop.service.mapper.ProductsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Products.
 */
@Service
@Transactional
public class ProductsServiceImpl implements ProductsService{

    private final Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);

    private final ProductsRepository productsRepository;

    private final ProductsMapper productsMapper;

    private final ProductsSearchRepository productsSearchRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductsMapper productsMapper, ProductsSearchRepository productsSearchRepository) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
        this.productsSearchRepository = productsSearchRepository;
    }

    /**
     * Save a products.
     *
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
        return result;
    }

    /**
     *  Get all the products.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productsRepository.findAll(pageable)
            .map(productsMapper::toDto);
    }

    /**
     *  Get one products by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductsDTO findOne(Long id) {
        log.debug("Request to get Products : {}", id);
        Products products = productsRepository.findOne(id);
        return productsMapper.toDto(products);
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
    public Page<ProductsDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Products for query {}", query);
        Page<Products> result = productsSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(productsMapper::toDto);
    }
}

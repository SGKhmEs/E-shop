package com.social.eshop.service.impl;

import com.social.eshop.repository.CategoryRepository;
import com.social.eshop.repository.ProductInBucketRepository;
import com.social.eshop.repository.TagForProductRepository;
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


import java.util.List;

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

    private final ProductInBucketRepository productInBucketRepository;

    private final TagForProductRepository tagForProductRepository;

    private final CategoryRepository categoryRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository,
                               ProductsMapper productsMapper,
                               ProductsSearchRepository productsSearchRepository,
                               ProductInBucketRepository productInBucketRepository,
                               TagForProductRepository tagForProductRepository,
                               CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
        this.productsSearchRepository = productsSearchRepository;
        this.productInBucketRepository = productInBucketRepository;
        this.tagForProductRepository = tagForProductRepository;
        this.categoryRepository = categoryRepository;
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

    @Override
    @Transactional(readOnly = true)
    public List<ProductsDTO> findAllProductsInBucket(Long id) {
        log.debug("Request to get all Buckets");
        List<Products> products = productInBucketRepository.findAllProductsByBucketId(id);

        return productsMapper.toDto(products);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductsDTO> findAllProductsWithTag(Long id) {
        log.debug("Request to get all Products");
        List<Products> products = tagForProductRepository.findByTagId(id);
        return productsMapper.toDto(products);

    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductsDTO> findAllProductsInCategory(Long id) {
        log.debug("Request to get all Products");
        List<Products> products = categoryRepository.findByCategoryId(id);
        return productsMapper.toDto(products);

    }
}











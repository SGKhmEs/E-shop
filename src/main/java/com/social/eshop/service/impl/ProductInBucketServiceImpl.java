package com.social.eshop.service.impl;

import com.social.eshop.service.ProductInBucketService;
import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.repository.ProductInBucketRepository;
import com.social.eshop.repository.search.ProductInBucketSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ProductInBucket.
 */
@Service
@Transactional
public class ProductInBucketServiceImpl implements ProductInBucketService{

    private final Logger log = LoggerFactory.getLogger(ProductInBucketServiceImpl.class);

    private final ProductInBucketRepository productInBucketRepository;

    private final ProductInBucketSearchRepository productInBucketSearchRepository;

    public ProductInBucketServiceImpl(ProductInBucketRepository productInBucketRepository, ProductInBucketSearchRepository productInBucketSearchRepository) {
        this.productInBucketRepository = productInBucketRepository;
        this.productInBucketSearchRepository = productInBucketSearchRepository;
    }

    /**
     * Save a productInBucket.
     *
     * @param productInBucket the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductInBucket save(ProductInBucket productInBucket) {
        log.debug("Request to save ProductInBucket : {}", productInBucket);
        ProductInBucket result = productInBucketRepository.save(productInBucket);
        productInBucketSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the productInBuckets.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductInBucket> findAll() {
        log.debug("Request to get all ProductInBuckets");
        return productInBucketRepository.findAll();
    }

    /**
     *  Get one productInBucket by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductInBucket findOne(Long id) {
        log.debug("Request to get ProductInBucket : {}", id);
        return productInBucketRepository.findOne(id);
    }

    /**
     *  Delete the  productInBucket by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductInBucket : {}", id);
        productInBucketRepository.delete(id);
        productInBucketSearchRepository.delete(id);
    }

    /**
     * Search for the productInBucket corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductInBucket> search(String query) {
        log.debug("Request to search ProductInBuckets for query {}", query);
        return StreamSupport
            .stream(productInBucketSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

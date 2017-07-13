package com.social.eshop.service.impl;

import com.social.eshop.service.ProductInBucketService;
import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.repository.ProductInBucketRepository;
import com.social.eshop.repository.search.ProductInBucketSearchRepository;
import com.social.eshop.service.dto.ProductInBucketDTO;
import com.social.eshop.service.mapper.ProductInBucketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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

    private final ProductInBucketMapper productInBucketMapper;

    private final ProductInBucketSearchRepository productInBucketSearchRepository;

    public ProductInBucketServiceImpl(ProductInBucketRepository productInBucketRepository, ProductInBucketMapper productInBucketMapper, ProductInBucketSearchRepository productInBucketSearchRepository) {
        this.productInBucketRepository = productInBucketRepository;
        this.productInBucketMapper = productInBucketMapper;
        this.productInBucketSearchRepository = productInBucketSearchRepository;
    }

    /**
     * Save a productInBucket.
     *
     * @param productInBucketDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductInBucketDTO save(ProductInBucketDTO productInBucketDTO) {
        log.debug("Request to save ProductInBucket : {}", productInBucketDTO);
        ProductInBucket productInBucket = productInBucketMapper.toEntity(productInBucketDTO);
        productInBucket = productInBucketRepository.save(productInBucket);
        ProductInBucketDTO result = productInBucketMapper.toDto(productInBucket);
        productInBucketSearchRepository.save(productInBucket);
        return result;
    }

    /**
     *  Get all the productInBuckets.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductInBucketDTO> findAll() {
        log.debug("Request to get all ProductInBuckets");
        return productInBucketRepository.findAll().stream()
            .map(productInBucketMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one productInBucket by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductInBucketDTO findOne(Long id) {
        log.debug("Request to get ProductInBucket : {}", id);
        ProductInBucket productInBucket = productInBucketRepository.findOne(id);
        return productInBucketMapper.toDto(productInBucket);
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
    public List<ProductInBucketDTO> search(String query) {
        log.debug("Request to search ProductInBuckets for query {}", query);
        return StreamSupport
            .stream(productInBucketSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(productInBucketMapper::toDto)
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.BucketService;
import com.social.eshop.domain.Bucket;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.repository.search.BucketSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Bucket.
 */
@Service
@Transactional
public class BucketServiceImpl implements BucketService{

    private final Logger log = LoggerFactory.getLogger(BucketServiceImpl.class);

    private final BucketRepository bucketRepository;

    private final BucketSearchRepository bucketSearchRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, BucketSearchRepository bucketSearchRepository) {
        this.bucketRepository = bucketRepository;
        this.bucketSearchRepository = bucketSearchRepository;
    }

    /**
     * Save a bucket.
     *
     * @param bucket the entity to save
     * @return the persisted entity
     */
    @Override
    public Bucket save(Bucket bucket) {
        log.debug("Request to save Bucket : {}", bucket);
        Bucket result = bucketRepository.save(bucket);
        bucketSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the buckets.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bucket> findAll(Pageable pageable) {
        log.debug("Request to get all Buckets");
        return bucketRepository.findAll(pageable);
    }

    /**
     *  Get one bucket by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Bucket findOne(Long id) {
        log.debug("Request to get Bucket : {}", id);
        return bucketRepository.findOne(id);
    }

    /**
     *  Delete the  bucket by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bucket : {}", id);
        bucketRepository.delete(id);
        bucketSearchRepository.delete(id);
    }

    /**
     * Search for the bucket corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bucket> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Buckets for query {}", query);
        Page<Bucket> result = bucketSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}

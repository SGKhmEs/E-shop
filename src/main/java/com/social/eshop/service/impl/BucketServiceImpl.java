package com.social.eshop.service.impl;

import com.social.eshop.service.BucketService;
import com.social.eshop.domain.Bucket;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.repository.search.BucketSearchRepository;
import com.social.eshop.service.dto.BucketDTO;
import com.social.eshop.service.mapper.BucketMapper;
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

    private final BucketMapper bucketMapper;

    private final BucketSearchRepository bucketSearchRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, BucketMapper bucketMapper, BucketSearchRepository bucketSearchRepository) {
        this.bucketRepository = bucketRepository;
        this.bucketMapper = bucketMapper;
        this.bucketSearchRepository = bucketSearchRepository;
    }

    /**
     * Save a bucket.
     *
     * @param bucketDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BucketDTO save(BucketDTO bucketDTO) {
        log.debug("Request to save Bucket : {}", bucketDTO);
        Bucket bucket = bucketMapper.toEntity(bucketDTO);
        bucket = bucketRepository.save(bucket);
        BucketDTO result = bucketMapper.toDto(bucket);
        bucketSearchRepository.save(bucket);
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
    public Page<BucketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Buckets");
        return bucketRepository.findAll(pageable)
            .map(bucketMapper::toDto);
    }

    /**
     *  Get one bucket by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BucketDTO findOne(Long id) {
        log.debug("Request to get Bucket : {}", id);
        Bucket bucket = bucketRepository.findOne(id);
        return bucketMapper.toDto(bucket);
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
    public Page<BucketDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Buckets for query {}", query);
        Page<Bucket> result = bucketSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(bucketMapper::toDto);
    }
}

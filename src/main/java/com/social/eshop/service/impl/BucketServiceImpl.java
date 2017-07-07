package com.social.eshop.service.impl;

import com.social.eshop.service.BucketService;
import com.social.eshop.domain.Bucket;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.repository.search.BucketSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.BucketDTO;
import com.social.eshop.service.mapper.BucketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
>>>>>>> with_entities

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Bucket.
 */
@Service
@Transactional
public class BucketServiceImpl implements BucketService{

    private final Logger log = LoggerFactory.getLogger(BucketServiceImpl.class);
<<<<<<< HEAD

    private final BucketRepository bucketRepository;

    private final BucketSearchRepository bucketSearchRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, BucketSearchRepository bucketSearchRepository) {
        this.bucketRepository = bucketRepository;
=======
    
    private final BucketRepository bucketRepository;

    private final BucketMapper bucketMapper;

    private final BucketSearchRepository bucketSearchRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, BucketMapper bucketMapper, BucketSearchRepository bucketSearchRepository) {
        this.bucketRepository = bucketRepository;
        this.bucketMapper = bucketMapper;
>>>>>>> with_entities
        this.bucketSearchRepository = bucketSearchRepository;
    }

    /**
     * Save a bucket.
     *
<<<<<<< HEAD
     * @param bucket the entity to save
     * @return the persisted entity
     */
    @Override
    public Bucket save(Bucket bucket) {
        log.debug("Request to save Bucket : {}", bucket);
        Bucket result = bucketRepository.save(bucket);
        bucketSearchRepository.save(result);
=======
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
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the buckets.
<<<<<<< HEAD
     *
     *  @param pageable the pagination information
=======
     *  
>>>>>>> with_entities
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Bucket> findAll(Pageable pageable) {
        log.debug("Request to get all Buckets");
        return bucketRepository.findAll(pageable);
=======
    public List<BucketDTO> findAll() {
        log.debug("Request to get all Buckets");
        List<BucketDTO> result = bucketRepository.findAll().stream()
            .map(bucketMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one bucket by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Bucket findOne(Long id) {
        log.debug("Request to get Bucket : {}", id);
        return bucketRepository.findOne(id);
=======
    public BucketDTO findOne(Long id) {
        log.debug("Request to get Bucket : {}", id);
        Bucket bucket = bucketRepository.findOne(id);
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);
        return bucketDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD
     *  @param pageable the pagination information
=======
>>>>>>> with_entities
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Bucket> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Buckets for query {}", query);
        Page<Bucket> result = bucketSearchRepository.search(queryStringQuery(query), pageable);
        return result;
=======
    public List<BucketDTO> search(String query) {
        log.debug("Request to search Buckets for query {}", query);
        return StreamSupport
            .stream(bucketSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(bucketMapper::toDto)
            .collect(Collectors.toList());
>>>>>>> with_entities
    }
}

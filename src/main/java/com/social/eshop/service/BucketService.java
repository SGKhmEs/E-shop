package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Bucket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import com.social.eshop.service.dto.BucketDTO;
import java.util.List;
>>>>>>> with_entities

/**
 * Service Interface for managing Bucket.
 */
public interface BucketService {

    /**
     * Save a bucket.
     *
<<<<<<< HEAD
     * @param bucket the entity to save
     * @return the persisted entity
     */
    Bucket save(Bucket bucket);

    /**
     *  Get all the buckets.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Bucket> findAll(Pageable pageable);
=======
     * @param bucketDTO the entity to save
     * @return the persisted entity
     */
    BucketDTO save(BucketDTO bucketDTO);

    /**
     *  Get all the buckets.
     *  
     *  @return the list of entities
     */
    List<BucketDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" bucket.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Bucket findOne(Long id);
=======
    BucketDTO findOne(Long id);
>>>>>>> with_entities

    /**
     *  Delete the "id" bucket.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the bucket corresponding to the query.
     *
     *  @param query the query of the search
     *  
<<<<<<< HEAD
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Bucket> search(String query, Pageable pageable);
=======
     *  @return the list of entities
     */
    List<BucketDTO> search(String query);
>>>>>>> with_entities
}

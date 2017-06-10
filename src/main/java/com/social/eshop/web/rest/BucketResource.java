package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.Bucket;
import com.social.eshop.service.BucketService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Bucket.
 */
@RestController
@RequestMapping("/api")
public class BucketResource {

    private final Logger log = LoggerFactory.getLogger(BucketResource.class);

    private static final String ENTITY_NAME = "bucket";

    private final BucketService bucketService;

    public BucketResource(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    /**
     * POST  /buckets : Create a new bucket.
     *
     * @param bucket the bucket to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bucket, or with status 400 (Bad Request) if the bucket has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/buckets")
    @Timed
    public ResponseEntity<Bucket> createBucket(@RequestBody Bucket bucket) throws URISyntaxException {
        log.debug("REST request to save Bucket : {}", bucket);
        if (bucket.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bucket cannot already have an ID")).body(null);
        }
        Bucket result = bucketService.save(bucket);
        return ResponseEntity.created(new URI("/api/buckets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /buckets : Updates an existing bucket.
     *
     * @param bucket the bucket to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bucket,
     * or with status 400 (Bad Request) if the bucket is not valid,
     * or with status 500 (Internal Server Error) if the bucket couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/buckets")
    @Timed
    public ResponseEntity<Bucket> updateBucket(@RequestBody Bucket bucket) throws URISyntaxException {
        log.debug("REST request to update Bucket : {}", bucket);
        if (bucket.getId() == null) {
            return createBucket(bucket);
        }
        Bucket result = bucketService.save(bucket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bucket.getId().toString()))
            .body(result);
    }

    /**
     * GET  /buckets : get all the buckets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of buckets in body
     */
    @GetMapping("/buckets")
    @Timed
    public ResponseEntity<List<Bucket>> getAllBuckets(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Buckets");
        Page<Bucket> page = bucketService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/buckets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /buckets/:id : get the "id" bucket.
     *
     * @param id the id of the bucket to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bucket, or with status 404 (Not Found)
     */
    @GetMapping("/buckets/{id}")
    @Timed
    public ResponseEntity<Bucket> getBucket(@PathVariable Long id) {
        log.debug("REST request to get Bucket : {}", id);
        Bucket bucket = bucketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bucket));
    }

    /**
     * DELETE  /buckets/:id : delete the "id" bucket.
     *
     * @param id the id of the bucket to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/buckets/{id}")
    @Timed
    public ResponseEntity<Void> deleteBucket(@PathVariable Long id) {
        log.debug("REST request to delete Bucket : {}", id);
        bucketService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/buckets?query=:query : search for the bucket corresponding
     * to the query.
     *
     * @param query the query of the bucket search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/buckets")
    @Timed
    public ResponseEntity<List<Bucket>> searchBuckets(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Buckets for query {}", query);
        Page<Bucket> page = bucketService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/buckets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.TagForProductService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.TagForProductDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing TagForProduct.
 */
@RestController
@RequestMapping("/api")
public class TagForProductResource {

    private final Logger log = LoggerFactory.getLogger(TagForProductResource.class);

    private static final String ENTITY_NAME = "tagForProduct";

    private final TagForProductService tagForProductService;

    public TagForProductResource(TagForProductService tagForProductService) {
        this.tagForProductService = tagForProductService;
    }

    /**
     * POST  /tag-for-products : Create a new tagForProduct.
     *
     * @param tagForProductDTO the tagForProductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tagForProductDTO, or with status 400 (Bad Request) if the tagForProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tag-for-products")
    @Timed
    public ResponseEntity<TagForProductDTO> createTagForProduct(@RequestBody TagForProductDTO tagForProductDTO) throws URISyntaxException {
        log.debug("REST request to save TagForProduct : {}", tagForProductDTO);
        if (tagForProductDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tagForProduct cannot already have an ID")).body(null);
        }
        TagForProductDTO result = tagForProductService.save(tagForProductDTO);
        return ResponseEntity.created(new URI("/api/tag-for-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tag-for-products : Updates an existing tagForProduct.
     *
     * @param tagForProductDTO the tagForProductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tagForProductDTO,
     * or with status 400 (Bad Request) if the tagForProductDTO is not valid,
     * or with status 500 (Internal Server Error) if the tagForProductDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tag-for-products")
    @Timed
    public ResponseEntity<TagForProductDTO> updateTagForProduct(@RequestBody TagForProductDTO tagForProductDTO) throws URISyntaxException {
        log.debug("REST request to update TagForProduct : {}", tagForProductDTO);
        if (tagForProductDTO.getId() == null) {
            return createTagForProduct(tagForProductDTO);
        }
        TagForProductDTO result = tagForProductService.save(tagForProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tagForProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tag-for-products : get all the tagForProducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tagForProducts in body
     */
    @GetMapping("/tag-for-products")
    @Timed
    public List<TagForProductDTO> getAllTagForProducts() {
        log.debug("REST request to get all TagForProducts");
        return tagForProductService.findAll();
    }

    /**
     * GET  /tag-for-products/:id : get the "id" tagForProduct.
     *
     * @param id the id of the tagForProductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tagForProductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tag-for-products/{id}")
    @Timed
    public ResponseEntity<TagForProductDTO> getTagForProduct(@PathVariable Long id) {
        log.debug("REST request to get TagForProduct : {}", id);
        TagForProductDTO tagForProductDTO = tagForProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tagForProductDTO));
    }

    /**
     * DELETE  /tag-for-products/:id : delete the "id" tagForProduct.
     *
     * @param id the id of the tagForProductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tag-for-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteTagForProduct(@PathVariable Long id) {
        log.debug("REST request to delete TagForProduct : {}", id);
        tagForProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tag-for-products?query=:query : search for the tagForProduct corresponding
     * to the query.
     *
     * @param query the query of the tagForProduct search
     * @return the result of the search
     */
    @GetMapping("/_search/tag-for-products")
    @Timed
    public List<TagForProductDTO> searchTagForProducts(@RequestParam String query) {
        log.debug("REST request to search TagForProducts for query {}", query);
        return tagForProductService.search(query);
    }

}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.SubCategoryService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.SubCategoryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing SubCategory.
 */
@RestController
@RequestMapping("/api")
public class SubCategoryResource {

    private final Logger log = LoggerFactory.getLogger(SubCategoryResource.class);

    private static final String ENTITY_NAME = "subCategory";

    private final SubCategoryService subCategoryService;

    public SubCategoryResource(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    /**
     * POST  /sub-categories : Create a new subCategory.
     *
     * @param subCategoryDTO the subCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subCategoryDTO, or with status 400 (Bad Request) if the subCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-categories")
    @Timed
    public ResponseEntity<SubCategoryDTO> createSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save SubCategory : {}", subCategoryDTO);
        if (subCategoryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new subCategory cannot already have an ID")).body(null);
        }
        SubCategoryDTO result = subCategoryService.save(subCategoryDTO);
        return ResponseEntity.created(new URI("/api/sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-categories : Updates an existing subCategory.
     *
     * @param subCategoryDTO the subCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subCategoryDTO,
     * or with status 400 (Bad Request) if the subCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the subCategoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-categories")
    @Timed
    public ResponseEntity<SubCategoryDTO> updateSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update SubCategory : {}", subCategoryDTO);
        if (subCategoryDTO.getId() == null) {
            return createSubCategory(subCategoryDTO);
        }
        SubCategoryDTO result = subCategoryService.save(subCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-categories : get all the subCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subCategories in body
     */
    @GetMapping("/sub-categories")
    @Timed
    public List<SubCategoryDTO> getAllSubCategories() {
        log.debug("REST request to get all SubCategories");
        return subCategoryService.findAll();
    }

    /**
     * GET  /sub-categories/:id : get the "id" subCategory.
     *
     * @param id the id of the subCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sub-categories/{id}")
    @Timed
    public ResponseEntity<SubCategoryDTO> getSubCategory(@PathVariable Long id) {
        log.debug("REST request to get SubCategory : {}", id);
        SubCategoryDTO subCategoryDTO = subCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subCategoryDTO));
    }

    /**
     * DELETE  /sub-categories/:id : delete the "id" subCategory.
     *
     * @param id the id of the subCategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete SubCategory : {}", id);
        subCategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sub-categories?query=:query : search for the subCategory corresponding
     * to the query.
     *
     * @param query the query of the subCategory search
     * @return the result of the search
     */
    @GetMapping("/_search/sub-categories")
    @Timed
    public List<SubCategoryDTO> searchSubCategories(@RequestParam String query) {
        log.debug("REST request to search SubCategories for query {}", query);
        return subCategoryService.search(query);
    }

}

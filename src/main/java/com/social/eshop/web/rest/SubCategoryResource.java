package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.SubCategory;
import com.social.eshop.service.SubCategoryService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.SubCategoryService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.SubCategoryDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final SubCategoryService subCategoryService;

    public SubCategoryResource(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    /**
     * POST  /sub-categories : Create a new subCategory.
     *
<<<<<<< HEAD
     * @param subCategory the subCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subCategory, or with status 400 (Bad Request) if the subCategory has already an ID
=======
     * @param subCategoryDTO the subCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subCategoryDTO, or with status 400 (Bad Request) if the subCategory has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-categories")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<SubCategory> createSubCategory(@Valid @RequestBody SubCategory subCategory) throws URISyntaxException {
        log.debug("REST request to save SubCategory : {}", subCategory);
        if (subCategory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new subCategory cannot already have an ID")).body(null);
        }
        SubCategory result = subCategoryService.save(subCategory);
=======
    public ResponseEntity<SubCategoryDTO> createSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save SubCategory : {}", subCategoryDTO);
        if (subCategoryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new subCategory cannot already have an ID")).body(null);
        }
        SubCategoryDTO result = subCategoryService.save(subCategoryDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-categories : Updates an existing subCategory.
     *
<<<<<<< HEAD
     * @param subCategory the subCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subCategory,
     * or with status 400 (Bad Request) if the subCategory is not valid,
     * or with status 500 (Internal Server Error) if the subCategory couldn't be updated
=======
     * @param subCategoryDTO the subCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subCategoryDTO,
     * or with status 400 (Bad Request) if the subCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the subCategoryDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-categories")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<SubCategory> updateSubCategory(@Valid @RequestBody SubCategory subCategory) throws URISyntaxException {
        log.debug("REST request to update SubCategory : {}", subCategory);
        if (subCategory.getId() == null) {
            return createSubCategory(subCategory);
        }
        SubCategory result = subCategoryService.save(subCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subCategory.getId().toString()))
=======
    public ResponseEntity<SubCategoryDTO> updateSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update SubCategory : {}", subCategoryDTO);
        if (subCategoryDTO.getId() == null) {
            return createSubCategory(subCategoryDTO);
        }
        SubCategoryDTO result = subCategoryService.save(subCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subCategoryDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /sub-categories : get all the subCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subCategories in body
     */
    @GetMapping("/sub-categories")
    @Timed
<<<<<<< HEAD
    public List<SubCategory> getAllSubCategories() {
=======
    public List<SubCategoryDTO> getAllSubCategories() {
>>>>>>> with_entities
        log.debug("REST request to get all SubCategories");
        return subCategoryService.findAll();
    }

    /**
     * GET  /sub-categories/:id : get the "id" subCategory.
     *
<<<<<<< HEAD
     * @param id the id of the subCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subCategory, or with status 404 (Not Found)
     */
    @GetMapping("/sub-categories/{id}")
    @Timed
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable Long id) {
        log.debug("REST request to get SubCategory : {}", id);
        SubCategory subCategory = subCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subCategory));
=======
     * @param id the id of the subCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sub-categories/{id}")
    @Timed
    public ResponseEntity<SubCategoryDTO> getSubCategory(@PathVariable Long id) {
        log.debug("REST request to get SubCategory : {}", id);
        SubCategoryDTO subCategoryDTO = subCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subCategoryDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /sub-categories/:id : delete the "id" subCategory.
     *
<<<<<<< HEAD
     * @param id the id of the subCategory to delete
=======
     * @param id the id of the subCategoryDTO to delete
>>>>>>> with_entities
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
<<<<<<< HEAD
     * @param query the query of the subCategory search
=======
     * @param query the query of the subCategory search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/sub-categories")
    @Timed
<<<<<<< HEAD
    public List<SubCategory> searchSubCategories(@RequestParam String query) {
=======
    public List<SubCategoryDTO> searchSubCategories(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search SubCategories for query {}", query);
        return subCategoryService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

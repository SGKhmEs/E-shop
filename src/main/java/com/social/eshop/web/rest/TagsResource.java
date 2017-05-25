package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.TagsService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.TagsDTO;
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
 * REST controller for managing Tags.
 */
@RestController
@RequestMapping("/api")
public class TagsResource {

    private final Logger log = LoggerFactory.getLogger(TagsResource.class);

    private static final String ENTITY_NAME = "tags";
        
    private final TagsService tagsService;

    public TagsResource(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    /**
     * POST  /tags : Create a new tags.
     *
     * @param tagsDTO the tagsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tagsDTO, or with status 400 (Bad Request) if the tags has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tags")
    @Timed
    public ResponseEntity<TagsDTO> createTags(@Valid @RequestBody TagsDTO tagsDTO) throws URISyntaxException {
        log.debug("REST request to save Tags : {}", tagsDTO);
        if (tagsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tags cannot already have an ID")).body(null);
        }
        TagsDTO result = tagsService.save(tagsDTO);
        return ResponseEntity.created(new URI("/api/tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tags : Updates an existing tags.
     *
     * @param tagsDTO the tagsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tagsDTO,
     * or with status 400 (Bad Request) if the tagsDTO is not valid,
     * or with status 500 (Internal Server Error) if the tagsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tags")
    @Timed
    public ResponseEntity<TagsDTO> updateTags(@Valid @RequestBody TagsDTO tagsDTO) throws URISyntaxException {
        log.debug("REST request to update Tags : {}", tagsDTO);
        if (tagsDTO.getId() == null) {
            return createTags(tagsDTO);
        }
        TagsDTO result = tagsService.save(tagsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tagsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tags : get all the tags.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tags in body
     */
    @GetMapping("/tags")
    @Timed
    public List<TagsDTO> getAllTags() {
        log.debug("REST request to get all Tags");
        return tagsService.findAll();
    }

    /**
     * GET  /tags/:id : get the "id" tags.
     *
     * @param id the id of the tagsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tagsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tags/{id}")
    @Timed
    public ResponseEntity<TagsDTO> getTags(@PathVariable Long id) {
        log.debug("REST request to get Tags : {}", id);
        TagsDTO tagsDTO = tagsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tagsDTO));
    }

    /**
     * DELETE  /tags/:id : delete the "id" tags.
     *
     * @param id the id of the tagsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tags/{id}")
    @Timed
    public ResponseEntity<Void> deleteTags(@PathVariable Long id) {
        log.debug("REST request to delete Tags : {}", id);
        tagsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tags?query=:query : search for the tags corresponding
     * to the query.
     *
     * @param query the query of the tags search 
     * @return the result of the search
     */
    @GetMapping("/_search/tags")
    @Timed
    public List<TagsDTO> searchTags(@RequestParam String query) {
        log.debug("REST request to search Tags for query {}", query);
        return tagsService.search(query);
    }


}

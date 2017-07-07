package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.Options;
import com.social.eshop.service.OptionsService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.OptionsService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.OptionsDTO;
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
 * REST controller for managing Options.
 */
@RestController
@RequestMapping("/api")
public class OptionsResource {

    private final Logger log = LoggerFactory.getLogger(OptionsResource.class);

    private static final String ENTITY_NAME = "options";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final OptionsService optionsService;

    public OptionsResource(OptionsService optionsService) {
        this.optionsService = optionsService;
    }

    /**
     * POST  /options : Create a new options.
     *
<<<<<<< HEAD
     * @param options the options to create
     * @return the ResponseEntity with status 201 (Created) and with body the new options, or with status 400 (Bad Request) if the options has already an ID
=======
     * @param optionsDTO the optionsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new optionsDTO, or with status 400 (Bad Request) if the options has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/options")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Options> createOptions(@Valid @RequestBody Options options) throws URISyntaxException {
        log.debug("REST request to save Options : {}", options);
        if (options.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new options cannot already have an ID")).body(null);
        }
        Options result = optionsService.save(options);
=======
    public ResponseEntity<OptionsDTO> createOptions(@Valid @RequestBody OptionsDTO optionsDTO) throws URISyntaxException {
        log.debug("REST request to save Options : {}", optionsDTO);
        if (optionsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new options cannot already have an ID")).body(null);
        }
        OptionsDTO result = optionsService.save(optionsDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /options : Updates an existing options.
     *
<<<<<<< HEAD
     * @param options the options to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated options,
     * or with status 400 (Bad Request) if the options is not valid,
     * or with status 500 (Internal Server Error) if the options couldn't be updated
=======
     * @param optionsDTO the optionsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated optionsDTO,
     * or with status 400 (Bad Request) if the optionsDTO is not valid,
     * or with status 500 (Internal Server Error) if the optionsDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/options")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Options> updateOptions(@Valid @RequestBody Options options) throws URISyntaxException {
        log.debug("REST request to update Options : {}", options);
        if (options.getId() == null) {
            return createOptions(options);
        }
        Options result = optionsService.save(options);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, options.getId().toString()))
=======
    public ResponseEntity<OptionsDTO> updateOptions(@Valid @RequestBody OptionsDTO optionsDTO) throws URISyntaxException {
        log.debug("REST request to update Options : {}", optionsDTO);
        if (optionsDTO.getId() == null) {
            return createOptions(optionsDTO);
        }
        OptionsDTO result = optionsService.save(optionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, optionsDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /options : get all the options.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of options in body
     */
    @GetMapping("/options")
    @Timed
<<<<<<< HEAD
    public List<Options> getAllOptions() {
=======
    public List<OptionsDTO> getAllOptions() {
>>>>>>> with_entities
        log.debug("REST request to get all Options");
        return optionsService.findAll();
    }

    /**
     * GET  /options/:id : get the "id" options.
     *
<<<<<<< HEAD
     * @param id the id of the options to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the options, or with status 404 (Not Found)
     */
    @GetMapping("/options/{id}")
    @Timed
    public ResponseEntity<Options> getOptions(@PathVariable Long id) {
        log.debug("REST request to get Options : {}", id);
        Options options = optionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(options));
=======
     * @param id the id of the optionsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the optionsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/options/{id}")
    @Timed
    public ResponseEntity<OptionsDTO> getOptions(@PathVariable Long id) {
        log.debug("REST request to get Options : {}", id);
        OptionsDTO optionsDTO = optionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(optionsDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /options/:id : delete the "id" options.
     *
<<<<<<< HEAD
     * @param id the id of the options to delete
=======
     * @param id the id of the optionsDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/options/{id}")
    @Timed
    public ResponseEntity<Void> deleteOptions(@PathVariable Long id) {
        log.debug("REST request to delete Options : {}", id);
        optionsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/options?query=:query : search for the options corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the options search
=======
     * @param query the query of the options search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/options")
    @Timed
<<<<<<< HEAD
    public List<Options> searchOptions(@RequestParam String query) {
=======
    public List<OptionsDTO> searchOptions(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search Options for query {}", query);
        return optionsService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

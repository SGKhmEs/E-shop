package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.Value;
import com.social.eshop.service.ValueService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.ValueService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ValueDTO;
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
 * REST controller for managing Value.
 */
@RestController
@RequestMapping("/api")
public class ValueResource {

    private final Logger log = LoggerFactory.getLogger(ValueResource.class);

    private static final String ENTITY_NAME = "value";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final ValueService valueService;

    public ValueResource(ValueService valueService) {
        this.valueService = valueService;
    }

    /**
     * POST  /values : Create a new value.
     *
<<<<<<< HEAD
     * @param value the value to create
     * @return the ResponseEntity with status 201 (Created) and with body the new value, or with status 400 (Bad Request) if the value has already an ID
=======
     * @param valueDTO the valueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new valueDTO, or with status 400 (Bad Request) if the value has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/values")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Value> createValue(@Valid @RequestBody Value value) throws URISyntaxException {
        log.debug("REST request to save Value : {}", value);
        if (value.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new value cannot already have an ID")).body(null);
        }
        Value result = valueService.save(value);
=======
    public ResponseEntity<ValueDTO> createValue(@Valid @RequestBody ValueDTO valueDTO) throws URISyntaxException {
        log.debug("REST request to save Value : {}", valueDTO);
        if (valueDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new value cannot already have an ID")).body(null);
        }
        ValueDTO result = valueService.save(valueDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /values : Updates an existing value.
     *
<<<<<<< HEAD
     * @param value the value to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated value,
     * or with status 400 (Bad Request) if the value is not valid,
     * or with status 500 (Internal Server Error) if the value couldn't be updated
=======
     * @param valueDTO the valueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated valueDTO,
     * or with status 400 (Bad Request) if the valueDTO is not valid,
     * or with status 500 (Internal Server Error) if the valueDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/values")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Value> updateValue(@Valid @RequestBody Value value) throws URISyntaxException {
        log.debug("REST request to update Value : {}", value);
        if (value.getId() == null) {
            return createValue(value);
        }
        Value result = valueService.save(value);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, value.getId().toString()))
=======
    public ResponseEntity<ValueDTO> updateValue(@Valid @RequestBody ValueDTO valueDTO) throws URISyntaxException {
        log.debug("REST request to update Value : {}", valueDTO);
        if (valueDTO.getId() == null) {
            return createValue(valueDTO);
        }
        ValueDTO result = valueService.save(valueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, valueDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /values : get all the values.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of values in body
     */
    @GetMapping("/values")
    @Timed
<<<<<<< HEAD
    public List<Value> getAllValues() {
=======
    public List<ValueDTO> getAllValues() {
>>>>>>> with_entities
        log.debug("REST request to get all Values");
        return valueService.findAll();
    }

    /**
     * GET  /values/:id : get the "id" value.
     *
<<<<<<< HEAD
     * @param id the id of the value to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the value, or with status 404 (Not Found)
     */
    @GetMapping("/values/{id}")
    @Timed
    public ResponseEntity<Value> getValue(@PathVariable Long id) {
        log.debug("REST request to get Value : {}", id);
        Value value = valueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(value));
=======
     * @param id the id of the valueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the valueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/values/{id}")
    @Timed
    public ResponseEntity<ValueDTO> getValue(@PathVariable Long id) {
        log.debug("REST request to get Value : {}", id);
        ValueDTO valueDTO = valueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(valueDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /values/:id : delete the "id" value.
     *
<<<<<<< HEAD
     * @param id the id of the value to delete
=======
     * @param id the id of the valueDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/values/{id}")
    @Timed
    public ResponseEntity<Void> deleteValue(@PathVariable Long id) {
        log.debug("REST request to delete Value : {}", id);
        valueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/values?query=:query : search for the value corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the value search
=======
     * @param query the query of the value search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/values")
    @Timed
<<<<<<< HEAD
    public List<Value> searchValues(@RequestParam String query) {
=======
    public List<ValueDTO> searchValues(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search Values for query {}", query);
        return valueService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

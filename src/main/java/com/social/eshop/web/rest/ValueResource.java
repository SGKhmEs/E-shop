package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.ValueService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ValueDTO;
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
        
    private final ValueService valueService;

    public ValueResource(ValueService valueService) {
        this.valueService = valueService;
    }

    /**
     * POST  /values : Create a new value.
     *
     * @param valueDTO the valueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new valueDTO, or with status 400 (Bad Request) if the value has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/values")
    @Timed
    public ResponseEntity<ValueDTO> createValue(@Valid @RequestBody ValueDTO valueDTO) throws URISyntaxException {
        log.debug("REST request to save Value : {}", valueDTO);
        if (valueDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new value cannot already have an ID")).body(null);
        }
        ValueDTO result = valueService.save(valueDTO);
        return ResponseEntity.created(new URI("/api/values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /values : Updates an existing value.
     *
     * @param valueDTO the valueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated valueDTO,
     * or with status 400 (Bad Request) if the valueDTO is not valid,
     * or with status 500 (Internal Server Error) if the valueDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/values")
    @Timed
    public ResponseEntity<ValueDTO> updateValue(@Valid @RequestBody ValueDTO valueDTO) throws URISyntaxException {
        log.debug("REST request to update Value : {}", valueDTO);
        if (valueDTO.getId() == null) {
            return createValue(valueDTO);
        }
        ValueDTO result = valueService.save(valueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, valueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /values : get all the values.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of values in body
     */
    @GetMapping("/values")
    @Timed
    public List<ValueDTO> getAllValues() {
        log.debug("REST request to get all Values");
        return valueService.findAll();
    }

    /**
     * GET  /values/:id : get the "id" value.
     *
     * @param id the id of the valueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the valueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/values/{id}")
    @Timed
    public ResponseEntity<ValueDTO> getValue(@PathVariable Long id) {
        log.debug("REST request to get Value : {}", id);
        ValueDTO valueDTO = valueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(valueDTO));
    }

    /**
     * DELETE  /values/:id : delete the "id" value.
     *
     * @param id the id of the valueDTO to delete
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
     * @param query the query of the value search 
     * @return the result of the search
     */
    @GetMapping("/_search/values")
    @Timed
    public List<ValueDTO> searchValues(@RequestParam String query) {
        log.debug("REST request to search Values for query {}", query);
        return valueService.search(query);
    }


}

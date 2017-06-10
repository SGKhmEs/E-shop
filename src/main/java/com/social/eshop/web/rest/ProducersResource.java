package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.Producers;
import com.social.eshop.service.ProducersService;
import com.social.eshop.web.rest.util.HeaderUtil;
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
 * REST controller for managing Producers.
 */
@RestController
@RequestMapping("/api")
public class ProducersResource {

    private final Logger log = LoggerFactory.getLogger(ProducersResource.class);

    private static final String ENTITY_NAME = "producers";

    private final ProducersService producersService;

    public ProducersResource(ProducersService producersService) {
        this.producersService = producersService;
    }

    /**
     * POST  /producers : Create a new producers.
     *
     * @param producers the producers to create
     * @return the ResponseEntity with status 201 (Created) and with body the new producers, or with status 400 (Bad Request) if the producers has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/producers")
    @Timed
    public ResponseEntity<Producers> createProducers(@Valid @RequestBody Producers producers) throws URISyntaxException {
        log.debug("REST request to save Producers : {}", producers);
        if (producers.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new producers cannot already have an ID")).body(null);
        }
        Producers result = producersService.save(producers);
        return ResponseEntity.created(new URI("/api/producers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /producers : Updates an existing producers.
     *
     * @param producers the producers to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated producers,
     * or with status 400 (Bad Request) if the producers is not valid,
     * or with status 500 (Internal Server Error) if the producers couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/producers")
    @Timed
    public ResponseEntity<Producers> updateProducers(@Valid @RequestBody Producers producers) throws URISyntaxException {
        log.debug("REST request to update Producers : {}", producers);
        if (producers.getId() == null) {
            return createProducers(producers);
        }
        Producers result = producersService.save(producers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, producers.getId().toString()))
            .body(result);
    }

    /**
     * GET  /producers : get all the producers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of producers in body
     */
    @GetMapping("/producers")
    @Timed
    public List<Producers> getAllProducers() {
        log.debug("REST request to get all Producers");
        return producersService.findAll();
    }

    /**
     * GET  /producers/:id : get the "id" producers.
     *
     * @param id the id of the producers to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the producers, or with status 404 (Not Found)
     */
    @GetMapping("/producers/{id}")
    @Timed
    public ResponseEntity<Producers> getProducers(@PathVariable Long id) {
        log.debug("REST request to get Producers : {}", id);
        Producers producers = producersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(producers));
    }

    /**
     * DELETE  /producers/:id : delete the "id" producers.
     *
     * @param id the id of the producers to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/producers/{id}")
    @Timed
    public ResponseEntity<Void> deleteProducers(@PathVariable Long id) {
        log.debug("REST request to delete Producers : {}", id);
        producersService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/producers?query=:query : search for the producers corresponding
     * to the query.
     *
     * @param query the query of the producers search
     * @return the result of the search
     */
    @GetMapping("/_search/producers")
    @Timed
    public List<Producers> searchProducers(@RequestParam String query) {
        log.debug("REST request to search Producers for query {}", query);
        return producersService.search(query);
    }

}

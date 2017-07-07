package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.Producers;
import com.social.eshop.service.ProducersService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.ProducersService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ProducersDTO;
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
 * REST controller for managing Producers.
 */
@RestController
@RequestMapping("/api")
public class ProducersResource {

    private final Logger log = LoggerFactory.getLogger(ProducersResource.class);

    private static final String ENTITY_NAME = "producers";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final ProducersService producersService;

    public ProducersResource(ProducersService producersService) {
        this.producersService = producersService;
    }

    /**
     * POST  /producers : Create a new producers.
     *
<<<<<<< HEAD
     * @param producers the producers to create
     * @return the ResponseEntity with status 201 (Created) and with body the new producers, or with status 400 (Bad Request) if the producers has already an ID
=======
     * @param producersDTO the producersDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new producersDTO, or with status 400 (Bad Request) if the producers has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/producers")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Producers> createProducers(@Valid @RequestBody Producers producers) throws URISyntaxException {
        log.debug("REST request to save Producers : {}", producers);
        if (producers.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new producers cannot already have an ID")).body(null);
        }
        Producers result = producersService.save(producers);
=======
    public ResponseEntity<ProducersDTO> createProducers(@Valid @RequestBody ProducersDTO producersDTO) throws URISyntaxException {
        log.debug("REST request to save Producers : {}", producersDTO);
        if (producersDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new producers cannot already have an ID")).body(null);
        }
        ProducersDTO result = producersService.save(producersDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/producers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /producers : Updates an existing producers.
     *
<<<<<<< HEAD
     * @param producers the producers to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated producers,
     * or with status 400 (Bad Request) if the producers is not valid,
     * or with status 500 (Internal Server Error) if the producers couldn't be updated
=======
     * @param producersDTO the producersDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated producersDTO,
     * or with status 400 (Bad Request) if the producersDTO is not valid,
     * or with status 500 (Internal Server Error) if the producersDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/producers")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Producers> updateProducers(@Valid @RequestBody Producers producers) throws URISyntaxException {
        log.debug("REST request to update Producers : {}", producers);
        if (producers.getId() == null) {
            return createProducers(producers);
        }
        Producers result = producersService.save(producers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, producers.getId().toString()))
=======
    public ResponseEntity<ProducersDTO> updateProducers(@Valid @RequestBody ProducersDTO producersDTO) throws URISyntaxException {
        log.debug("REST request to update Producers : {}", producersDTO);
        if (producersDTO.getId() == null) {
            return createProducers(producersDTO);
        }
        ProducersDTO result = producersService.save(producersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, producersDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /producers : get all the producers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of producers in body
     */
    @GetMapping("/producers")
    @Timed
<<<<<<< HEAD
    public List<Producers> getAllProducers() {
=======
    public List<ProducersDTO> getAllProducers() {
>>>>>>> with_entities
        log.debug("REST request to get all Producers");
        return producersService.findAll();
    }

    /**
     * GET  /producers/:id : get the "id" producers.
     *
<<<<<<< HEAD
     * @param id the id of the producers to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the producers, or with status 404 (Not Found)
     */
    @GetMapping("/producers/{id}")
    @Timed
    public ResponseEntity<Producers> getProducers(@PathVariable Long id) {
        log.debug("REST request to get Producers : {}", id);
        Producers producers = producersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(producers));
=======
     * @param id the id of the producersDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the producersDTO, or with status 404 (Not Found)
     */
    @GetMapping("/producers/{id}")
    @Timed
    public ResponseEntity<ProducersDTO> getProducers(@PathVariable Long id) {
        log.debug("REST request to get Producers : {}", id);
        ProducersDTO producersDTO = producersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(producersDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /producers/:id : delete the "id" producers.
     *
<<<<<<< HEAD
     * @param id the id of the producers to delete
=======
     * @param id the id of the producersDTO to delete
>>>>>>> with_entities
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
<<<<<<< HEAD
     * @param query the query of the producers search
=======
     * @param query the query of the producers search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/producers")
    @Timed
<<<<<<< HEAD
    public List<Producers> searchProducers(@RequestParam String query) {
=======
    public List<ProducersDTO> searchProducers(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search Producers for query {}", query);
        return producersService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

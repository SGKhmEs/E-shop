package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.ProducersService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ProducersDTO;
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
     * @param producersDTO the producersDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new producersDTO, or with status 400 (Bad Request) if the producers has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/producers")
    @Timed
    public ResponseEntity<ProducersDTO> createProducers(@Valid @RequestBody ProducersDTO producersDTO) throws URISyntaxException {
        log.debug("REST request to save Producers : {}", producersDTO);
        if (producersDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new producers cannot already have an ID")).body(null);
        }
        ProducersDTO result = producersService.save(producersDTO);
        return ResponseEntity.created(new URI("/api/producers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /producers : Updates an existing producers.
     *
     * @param producersDTO the producersDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated producersDTO,
     * or with status 400 (Bad Request) if the producersDTO is not valid,
     * or with status 500 (Internal Server Error) if the producersDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/producers")
    @Timed
    public ResponseEntity<ProducersDTO> updateProducers(@Valid @RequestBody ProducersDTO producersDTO) throws URISyntaxException {
        log.debug("REST request to update Producers : {}", producersDTO);
        if (producersDTO.getId() == null) {
            return createProducers(producersDTO);
        }
        ProducersDTO result = producersService.save(producersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, producersDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /producers : get all the producers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of producers in body
     */
    @GetMapping("/producers")
    @Timed
    public List<ProducersDTO> getAllProducers() {
        log.debug("REST request to get all Producers");
        return producersService.findAll();
    }

    /**
     * GET  /producers/:id : get the "id" producers.
     *
     * @param id the id of the producersDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the producersDTO, or with status 404 (Not Found)
     */
    @GetMapping("/producers/{id}")
    @Timed
    public ResponseEntity<ProducersDTO> getProducers(@PathVariable Long id) {
        log.debug("REST request to get Producers : {}", id);
        ProducersDTO producersDTO = producersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(producersDTO));
    }

    /**
     * DELETE  /producers/:id : delete the "id" producers.
     *
     * @param id the id of the producersDTO to delete
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
    public List<ProducersDTO> searchProducers(@RequestParam String query) {
        log.debug("REST request to search Producers for query {}", query);
        return producersService.search(query);
    }

}

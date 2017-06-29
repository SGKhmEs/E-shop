package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.ConsignmentService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ConsignmentDTO;
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
 * REST controller for managing Consignment.
 */
@RestController
@RequestMapping("/api")
public class ConsignmentResource {

    private final Logger log = LoggerFactory.getLogger(ConsignmentResource.class);

    private static final String ENTITY_NAME = "consignment";

    private final ConsignmentService consignmentService;

    public ConsignmentResource(ConsignmentService consignmentService) {
        this.consignmentService = consignmentService;
    }

    /**
     * POST  /consignments : Create a new consignment.
     *
     * @param consignmentDTO the consignmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consignmentDTO, or with status 400 (Bad Request) if the consignment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consignments")
    @Timed
    public ResponseEntity<ConsignmentDTO> createConsignment(@Valid @RequestBody ConsignmentDTO consignmentDTO) throws URISyntaxException {
        log.debug("REST request to save Consignment : {}", consignmentDTO);
        if (consignmentDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new consignment cannot already have an ID")).body(null);
        }
        ConsignmentDTO result = consignmentService.save(consignmentDTO);
        return ResponseEntity.created(new URI("/api/consignments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consignments : Updates an existing consignment.
     *
     * @param consignmentDTO the consignmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consignmentDTO,
     * or with status 400 (Bad Request) if the consignmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the consignmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consignments")
    @Timed
    public ResponseEntity<ConsignmentDTO> updateConsignment(@Valid @RequestBody ConsignmentDTO consignmentDTO) throws URISyntaxException {
        log.debug("REST request to update Consignment : {}", consignmentDTO);
        if (consignmentDTO.getId() == null) {
            return createConsignment(consignmentDTO);
        }
        ConsignmentDTO result = consignmentService.save(consignmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consignmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consignments : get all the consignments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of consignments in body
     */
    @GetMapping("/consignments")
    @Timed
    public List<ConsignmentDTO> getAllConsignments() {
        log.debug("REST request to get all Consignments");
        return consignmentService.findAll();
    }

    /**
     * GET  /consignments/:id : get the "id" consignment.
     *
     * @param id the id of the consignmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consignmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/consignments/{id}")
    @Timed
    public ResponseEntity<ConsignmentDTO> getConsignment(@PathVariable Long id) {
        log.debug("REST request to get Consignment : {}", id);
        ConsignmentDTO consignmentDTO = consignmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(consignmentDTO));
    }

    /**
     * DELETE  /consignments/:id : delete the "id" consignment.
     *
     * @param id the id of the consignmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consignments/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsignment(@PathVariable Long id) {
        log.debug("REST request to delete Consignment : {}", id);
        consignmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/consignments?query=:query : search for the consignment corresponding
     * to the query.
     *
     * @param query the query of the consignment search
     * @return the result of the search
     */
    @GetMapping("/_search/consignments")
    @Timed
    public List<ConsignmentDTO> searchConsignments(@RequestParam String query) {
        log.debug("REST request to search Consignments for query {}", query);
        return consignmentService.search(query);
    }

}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.ConfirmService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ConfirmDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Confirm.
 */
@RestController
@RequestMapping("/api")
public class ConfirmResource {

    private final Logger log = LoggerFactory.getLogger(ConfirmResource.class);

    private static final String ENTITY_NAME = "confirm";
        
    private final ConfirmService confirmService;

    public ConfirmResource(ConfirmService confirmService) {
        this.confirmService = confirmService;
    }

    /**
     * POST  /confirms : Create a new confirm.
     *
     * @param confirmDTO the confirmDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new confirmDTO, or with status 400 (Bad Request) if the confirm has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/confirms")
    @Timed
    public ResponseEntity<ConfirmDTO> createConfirm(@RequestBody ConfirmDTO confirmDTO) throws URISyntaxException {
        log.debug("REST request to save Confirm : {}", confirmDTO);
        if (confirmDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new confirm cannot already have an ID")).body(null);
        }
        ConfirmDTO result = confirmService.save(confirmDTO);
        return ResponseEntity.created(new URI("/api/confirms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /confirms : Updates an existing confirm.
     *
     * @param confirmDTO the confirmDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated confirmDTO,
     * or with status 400 (Bad Request) if the confirmDTO is not valid,
     * or with status 500 (Internal Server Error) if the confirmDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/confirms")
    @Timed
    public ResponseEntity<ConfirmDTO> updateConfirm(@RequestBody ConfirmDTO confirmDTO) throws URISyntaxException {
        log.debug("REST request to update Confirm : {}", confirmDTO);
        if (confirmDTO.getId() == null) {
            return createConfirm(confirmDTO);
        }
        ConfirmDTO result = confirmService.save(confirmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, confirmDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /confirms : get all the confirms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of confirms in body
     */
    @GetMapping("/confirms")
    @Timed
    public List<ConfirmDTO> getAllConfirms() {
        log.debug("REST request to get all Confirms");
        return confirmService.findAll();
    }

    /**
     * GET  /confirms/:id : get the "id" confirm.
     *
     * @param id the id of the confirmDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the confirmDTO, or with status 404 (Not Found)
     */
    @GetMapping("/confirms/{id}")
    @Timed
    public ResponseEntity<ConfirmDTO> getConfirm(@PathVariable Long id) {
        log.debug("REST request to get Confirm : {}", id);
        ConfirmDTO confirmDTO = confirmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(confirmDTO));
    }

    /**
     * DELETE  /confirms/:id : delete the "id" confirm.
     *
     * @param id the id of the confirmDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/confirms/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfirm(@PathVariable Long id) {
        log.debug("REST request to delete Confirm : {}", id);
        confirmService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/confirms?query=:query : search for the confirm corresponding
     * to the query.
     *
     * @param query the query of the confirm search 
     * @return the result of the search
     */
    @GetMapping("/_search/confirms")
    @Timed
    public List<ConfirmDTO> searchConfirms(@RequestParam String query) {
        log.debug("REST request to search Confirms for query {}", query);
        return confirmService.search(query);
    }


}

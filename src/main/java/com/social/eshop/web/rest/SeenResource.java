package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.SeenService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.SeenDTO;
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
 * REST controller for managing Seen.
 */
@RestController
@RequestMapping("/api")
public class SeenResource {

    private final Logger log = LoggerFactory.getLogger(SeenResource.class);

    private static final String ENTITY_NAME = "seen";

    private final SeenService seenService;

    public SeenResource(SeenService seenService) {
        this.seenService = seenService;
    }

    /**
     * POST  /seens : Create a new seen.
     *
     * @param seenDTO the seenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new seenDTO, or with status 400 (Bad Request) if the seen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/seens")
    @Timed
    public ResponseEntity<SeenDTO> createSeen(@RequestBody SeenDTO seenDTO) throws URISyntaxException {
        log.debug("REST request to save Seen : {}", seenDTO);
        if (seenDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new seen cannot already have an ID")).body(null);
        }
        SeenDTO result = seenService.save(seenDTO);
        return ResponseEntity.created(new URI("/api/seens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /seens : Updates an existing seen.
     *
     * @param seenDTO the seenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated seenDTO,
     * or with status 400 (Bad Request) if the seenDTO is not valid,
     * or with status 500 (Internal Server Error) if the seenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/seens")
    @Timed
    public ResponseEntity<SeenDTO> updateSeen(@RequestBody SeenDTO seenDTO) throws URISyntaxException {
        log.debug("REST request to update Seen : {}", seenDTO);
        if (seenDTO.getId() == null) {
            return createSeen(seenDTO);
        }
        SeenDTO result = seenService.save(seenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, seenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /seens : get all the seens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of seens in body
     */
    @GetMapping("/seens")
    @Timed
    public List<SeenDTO> getAllSeens() {
        log.debug("REST request to get all Seens");
        return seenService.findAll();
    }

    /**
     * GET  /seens/:id : get the "id" seen.
     *
     * @param id the id of the seenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the seenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/seens/{id}")
    @Timed
    public ResponseEntity<SeenDTO> getSeen(@PathVariable Long id) {
        log.debug("REST request to get Seen : {}", id);
        SeenDTO seenDTO = seenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(seenDTO));
    }

    /**
     * DELETE  /seens/:id : delete the "id" seen.
     *
     * @param id the id of the seenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/seens/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeen(@PathVariable Long id) {
        log.debug("REST request to delete Seen : {}", id);
        seenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/seens?query=:query : search for the seen corresponding
     * to the query.
     *
     * @param query the query of the seen search
     * @return the result of the search
     */
    @GetMapping("/_search/seens")
    @Timed
    public List<SeenDTO> searchSeens(@RequestParam String query) {
        log.debug("REST request to search Seens for query {}", query);
        return seenService.search(query);
    }

}

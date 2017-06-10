package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.Seen;
import com.social.eshop.service.SeenService;
import com.social.eshop.web.rest.util.HeaderUtil;
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
     * @param seen the seen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new seen, or with status 400 (Bad Request) if the seen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/seens")
    @Timed
    public ResponseEntity<Seen> createSeen(@RequestBody Seen seen) throws URISyntaxException {
        log.debug("REST request to save Seen : {}", seen);
        if (seen.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new seen cannot already have an ID")).body(null);
        }
        Seen result = seenService.save(seen);
        return ResponseEntity.created(new URI("/api/seens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /seens : Updates an existing seen.
     *
     * @param seen the seen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated seen,
     * or with status 400 (Bad Request) if the seen is not valid,
     * or with status 500 (Internal Server Error) if the seen couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/seens")
    @Timed
    public ResponseEntity<Seen> updateSeen(@RequestBody Seen seen) throws URISyntaxException {
        log.debug("REST request to update Seen : {}", seen);
        if (seen.getId() == null) {
            return createSeen(seen);
        }
        Seen result = seenService.save(seen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, seen.getId().toString()))
            .body(result);
    }

    /**
     * GET  /seens : get all the seens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of seens in body
     */
    @GetMapping("/seens")
    @Timed
    public List<Seen> getAllSeens() {
        log.debug("REST request to get all Seens");
        return seenService.findAll();
    }

    /**
     * GET  /seens/:id : get the "id" seen.
     *
     * @param id the id of the seen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the seen, or with status 404 (Not Found)
     */
    @GetMapping("/seens/{id}")
    @Timed
    public ResponseEntity<Seen> getSeen(@PathVariable Long id) {
        log.debug("REST request to get Seen : {}", id);
        Seen seen = seenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(seen));
    }

    /**
     * DELETE  /seens/:id : delete the "id" seen.
     *
     * @param id the id of the seen to delete
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
    public List<Seen> searchSeens(@RequestParam String query) {
        log.debug("REST request to search Seens for query {}", query);
        return seenService.search(query);
    }

}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.Seen;
import com.social.eshop.service.SeenService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.SeenService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.SeenDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final SeenService seenService;

    public SeenResource(SeenService seenService) {
        this.seenService = seenService;
    }

    /**
     * POST  /seens : Create a new seen.
     *
<<<<<<< HEAD
     * @param seen the seen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new seen, or with status 400 (Bad Request) if the seen has already an ID
=======
     * @param seenDTO the seenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new seenDTO, or with status 400 (Bad Request) if the seen has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/seens")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Seen> createSeen(@RequestBody Seen seen) throws URISyntaxException {
        log.debug("REST request to save Seen : {}", seen);
        if (seen.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new seen cannot already have an ID")).body(null);
        }
        Seen result = seenService.save(seen);
=======
    public ResponseEntity<SeenDTO> createSeen(@RequestBody SeenDTO seenDTO) throws URISyntaxException {
        log.debug("REST request to save Seen : {}", seenDTO);
        if (seenDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new seen cannot already have an ID")).body(null);
        }
        SeenDTO result = seenService.save(seenDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/seens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /seens : Updates an existing seen.
     *
<<<<<<< HEAD
     * @param seen the seen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated seen,
     * or with status 400 (Bad Request) if the seen is not valid,
     * or with status 500 (Internal Server Error) if the seen couldn't be updated
=======
     * @param seenDTO the seenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated seenDTO,
     * or with status 400 (Bad Request) if the seenDTO is not valid,
     * or with status 500 (Internal Server Error) if the seenDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/seens")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Seen> updateSeen(@RequestBody Seen seen) throws URISyntaxException {
        log.debug("REST request to update Seen : {}", seen);
        if (seen.getId() == null) {
            return createSeen(seen);
        }
        Seen result = seenService.save(seen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, seen.getId().toString()))
=======
    public ResponseEntity<SeenDTO> updateSeen(@RequestBody SeenDTO seenDTO) throws URISyntaxException {
        log.debug("REST request to update Seen : {}", seenDTO);
        if (seenDTO.getId() == null) {
            return createSeen(seenDTO);
        }
        SeenDTO result = seenService.save(seenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, seenDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /seens : get all the seens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of seens in body
     */
    @GetMapping("/seens")
    @Timed
<<<<<<< HEAD
    public List<Seen> getAllSeens() {
=======
    public List<SeenDTO> getAllSeens() {
>>>>>>> with_entities
        log.debug("REST request to get all Seens");
        return seenService.findAll();
    }

    /**
     * GET  /seens/:id : get the "id" seen.
     *
<<<<<<< HEAD
     * @param id the id of the seen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the seen, or with status 404 (Not Found)
     */
    @GetMapping("/seens/{id}")
    @Timed
    public ResponseEntity<Seen> getSeen(@PathVariable Long id) {
        log.debug("REST request to get Seen : {}", id);
        Seen seen = seenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(seen));
=======
     * @param id the id of the seenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the seenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/seens/{id}")
    @Timed
    public ResponseEntity<SeenDTO> getSeen(@PathVariable Long id) {
        log.debug("REST request to get Seen : {}", id);
        SeenDTO seenDTO = seenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(seenDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /seens/:id : delete the "id" seen.
     *
<<<<<<< HEAD
     * @param id the id of the seen to delete
=======
     * @param id the id of the seenDTO to delete
>>>>>>> with_entities
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
<<<<<<< HEAD
     * @param query the query of the seen search
=======
     * @param query the query of the seen search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/seens")
    @Timed
<<<<<<< HEAD
    public List<Seen> searchSeens(@RequestParam String query) {
=======
    public List<SeenDTO> searchSeens(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search Seens for query {}", query);
        return seenService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

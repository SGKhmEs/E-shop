package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.Manager;
import com.social.eshop.service.ManagerService;
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
 * REST controller for managing Manager.
 */
@RestController
@RequestMapping("/api")
public class ManagerResource {

    private final Logger log = LoggerFactory.getLogger(ManagerResource.class);

    private static final String ENTITY_NAME = "manager";

    private final ManagerService managerService;

    public ManagerResource(ManagerService managerService) {
        this.managerService = managerService;
    }

    /**
     * POST  /managers : Create a new manager.
     *
     * @param manager the manager to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manager, or with status 400 (Bad Request) if the manager has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/managers")
    @Timed
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) throws URISyntaxException {
        log.debug("REST request to save Manager : {}", manager);
        if (manager.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new manager cannot already have an ID")).body(null);
        }
        Manager result = managerService.save(manager);
        return ResponseEntity.created(new URI("/api/managers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /managers : Updates an existing manager.
     *
     * @param manager the manager to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manager,
     * or with status 400 (Bad Request) if the manager is not valid,
     * or with status 500 (Internal Server Error) if the manager couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/managers")
    @Timed
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) throws URISyntaxException {
        log.debug("REST request to update Manager : {}", manager);
        if (manager.getId() == null) {
            return createManager(manager);
        }
        Manager result = managerService.save(manager);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, manager.getId().toString()))
            .body(result);
    }

    /**
     * GET  /managers : get all the managers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of managers in body
     */
    @GetMapping("/managers")
    @Timed
    public List<Manager> getAllManagers() {
        log.debug("REST request to get all Managers");
        return managerService.findAll();
    }

    /**
     * GET  /managers/:id : get the "id" manager.
     *
     * @param id the id of the manager to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manager, or with status 404 (Not Found)
     */
    @GetMapping("/managers/{id}")
    @Timed
    public ResponseEntity<Manager> getManager(@PathVariable Long id) {
        log.debug("REST request to get Manager : {}", id);
        Manager manager = managerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(manager));
    }

    /**
     * DELETE  /managers/:id : delete the "id" manager.
     *
     * @param id the id of the manager to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/managers/{id}")
    @Timed
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        log.debug("REST request to delete Manager : {}", id);
        managerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/managers?query=:query : search for the manager corresponding
     * to the query.
     *
     * @param query the query of the manager search
     * @return the result of the search
     */
    @GetMapping("/_search/managers")
    @Timed
    public List<Manager> searchManagers(@RequestParam String query) {
        log.debug("REST request to search Managers for query {}", query);
        return managerService.search(query);
    }

}

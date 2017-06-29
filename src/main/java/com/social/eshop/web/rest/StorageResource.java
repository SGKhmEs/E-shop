package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.StorageService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.StorageDTO;
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
 * REST controller for managing Storage.
 */
@RestController
@RequestMapping("/api")
public class StorageResource {

    private final Logger log = LoggerFactory.getLogger(StorageResource.class);

    private static final String ENTITY_NAME = "storage";

    private final StorageService storageService;

    public StorageResource(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * POST  /storages : Create a new storage.
     *
     * @param storageDTO the storageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new storageDTO, or with status 400 (Bad Request) if the storage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/storages")
    @Timed
    public ResponseEntity<StorageDTO> createStorage(@Valid @RequestBody StorageDTO storageDTO) throws URISyntaxException {
        log.debug("REST request to save Storage : {}", storageDTO);
        if (storageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new storage cannot already have an ID")).body(null);
        }
        StorageDTO result = storageService.save(storageDTO);
        return ResponseEntity.created(new URI("/api/storages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /storages : Updates an existing storage.
     *
     * @param storageDTO the storageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated storageDTO,
     * or with status 400 (Bad Request) if the storageDTO is not valid,
     * or with status 500 (Internal Server Error) if the storageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/storages")
    @Timed
    public ResponseEntity<StorageDTO> updateStorage(@Valid @RequestBody StorageDTO storageDTO) throws URISyntaxException {
        log.debug("REST request to update Storage : {}", storageDTO);
        if (storageDTO.getId() == null) {
            return createStorage(storageDTO);
        }
        StorageDTO result = storageService.save(storageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, storageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /storages : get all the storages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of storages in body
     */
    @GetMapping("/storages")
    @Timed
    public List<StorageDTO> getAllStorages() {
        log.debug("REST request to get all Storages");
        return storageService.findAll();
    }

    /**
     * GET  /storages/:id : get the "id" storage.
     *
     * @param id the id of the storageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the storageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/storages/{id}")
    @Timed
    public ResponseEntity<StorageDTO> getStorage(@PathVariable Long id) {
        log.debug("REST request to get Storage : {}", id);
        StorageDTO storageDTO = storageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(storageDTO));
    }

    /**
     * DELETE  /storages/:id : delete the "id" storage.
     *
     * @param id the id of the storageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/storages/{id}")
    @Timed
    public ResponseEntity<Void> deleteStorage(@PathVariable Long id) {
        log.debug("REST request to delete Storage : {}", id);
        storageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/storages?query=:query : search for the storage corresponding
     * to the query.
     *
     * @param query the query of the storage search
     * @return the result of the search
     */
    @GetMapping("/_search/storages")
    @Timed
    public List<StorageDTO> searchStorages(@RequestParam String query) {
        log.debug("REST request to search Storages for query {}", query);
        return storageService.search(query);
    }

}

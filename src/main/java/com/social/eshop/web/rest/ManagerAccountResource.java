package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.ManagerAccountService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.ManagerAccountDTO;
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
 * REST controller for managing ManagerAccount.
 */
@RestController
@RequestMapping("/api")
public class ManagerAccountResource {

    private final Logger log = LoggerFactory.getLogger(ManagerAccountResource.class);

    private static final String ENTITY_NAME = "managerAccount";

    private final ManagerAccountService managerAccountService;

    public ManagerAccountResource(ManagerAccountService managerAccountService) {
        this.managerAccountService = managerAccountService;
    }

    /**
     * POST  /manager-accounts : Create a new managerAccount.
     *
     * @param managerAccountDTO the managerAccountDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new managerAccountDTO, or with status 400 (Bad Request) if the managerAccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/manager-accounts")
    @Timed
    public ResponseEntity<ManagerAccountDTO> createManagerAccount(@RequestBody ManagerAccountDTO managerAccountDTO) throws URISyntaxException {
        log.debug("REST request to save ManagerAccount : {}", managerAccountDTO);
        if (managerAccountDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new managerAccount cannot already have an ID")).body(null);
        }
        ManagerAccountDTO result = managerAccountService.save(managerAccountDTO);
        return ResponseEntity.created(new URI("/api/manager-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manager-accounts : Updates an existing managerAccount.
     *
     * @param managerAccountDTO the managerAccountDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated managerAccountDTO,
     * or with status 400 (Bad Request) if the managerAccountDTO is not valid,
     * or with status 500 (Internal Server Error) if the managerAccountDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/manager-accounts")
    @Timed
    public ResponseEntity<ManagerAccountDTO> updateManagerAccount(@RequestBody ManagerAccountDTO managerAccountDTO) throws URISyntaxException {
        log.debug("REST request to update ManagerAccount : {}", managerAccountDTO);
        if (managerAccountDTO.getId() == null) {
            return createManagerAccount(managerAccountDTO);
        }
        ManagerAccountDTO result = managerAccountService.save(managerAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, managerAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manager-accounts : get all the managerAccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of managerAccounts in body
     */
    @GetMapping("/manager-accounts")
    @Timed
    public List<ManagerAccountDTO> getAllManagerAccounts() {
        log.debug("REST request to get all ManagerAccounts");
        return managerAccountService.findAll();
    }

    /**
     * GET  /manager-accounts/:id : get the "id" managerAccount.
     *
     * @param id the id of the managerAccountDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the managerAccountDTO, or with status 404 (Not Found)
     */
    @GetMapping("/manager-accounts/{id}")
    @Timed
    public ResponseEntity<ManagerAccountDTO> getManagerAccount(@PathVariable Long id) {
        log.debug("REST request to get ManagerAccount : {}", id);
        ManagerAccountDTO managerAccountDTO = managerAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(managerAccountDTO));
    }

    /**
     * DELETE  /manager-accounts/:id : delete the "id" managerAccount.
     *
     * @param id the id of the managerAccountDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/manager-accounts/{id}")
    @Timed
    public ResponseEntity<Void> deleteManagerAccount(@PathVariable Long id) {
        log.debug("REST request to delete ManagerAccount : {}", id);
        managerAccountService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/manager-accounts?query=:query : search for the managerAccount corresponding
     * to the query.
     *
     * @param query the query of the managerAccount search
     * @return the result of the search
     */
    @GetMapping("/_search/manager-accounts")
    @Timed
    public List<ManagerAccountDTO> searchManagerAccounts(@RequestParam String query) {
        log.debug("REST request to search ManagerAccounts for query {}", query);
        return managerAccountService.search(query);
    }

}

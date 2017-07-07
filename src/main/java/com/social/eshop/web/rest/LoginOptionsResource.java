package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.LoginOptions;
import com.social.eshop.service.LoginOptionsService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.LoginOptionsService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.LoginOptionsDTO;
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
 * REST controller for managing LoginOptions.
 */
@RestController
@RequestMapping("/api")
public class LoginOptionsResource {

    private final Logger log = LoggerFactory.getLogger(LoginOptionsResource.class);

    private static final String ENTITY_NAME = "loginOptions";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final LoginOptionsService loginOptionsService;

    public LoginOptionsResource(LoginOptionsService loginOptionsService) {
        this.loginOptionsService = loginOptionsService;
    }

    /**
     * POST  /login-options : Create a new loginOptions.
     *
<<<<<<< HEAD
     * @param loginOptions the loginOptions to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loginOptions, or with status 400 (Bad Request) if the loginOptions has already an ID
=======
     * @param loginOptionsDTO the loginOptionsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loginOptionsDTO, or with status 400 (Bad Request) if the loginOptions has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/login-options")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<LoginOptions> createLoginOptions(@Valid @RequestBody LoginOptions loginOptions) throws URISyntaxException {
        log.debug("REST request to save LoginOptions : {}", loginOptions);
        if (loginOptions.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new loginOptions cannot already have an ID")).body(null);
        }
        LoginOptions result = loginOptionsService.save(loginOptions);
=======
    public ResponseEntity<LoginOptionsDTO> createLoginOptions(@Valid @RequestBody LoginOptionsDTO loginOptionsDTO) throws URISyntaxException {
        log.debug("REST request to save LoginOptions : {}", loginOptionsDTO);
        if (loginOptionsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new loginOptions cannot already have an ID")).body(null);
        }
        LoginOptionsDTO result = loginOptionsService.save(loginOptionsDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/login-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /login-options : Updates an existing loginOptions.
     *
<<<<<<< HEAD
     * @param loginOptions the loginOptions to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loginOptions,
     * or with status 400 (Bad Request) if the loginOptions is not valid,
     * or with status 500 (Internal Server Error) if the loginOptions couldn't be updated
=======
     * @param loginOptionsDTO the loginOptionsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loginOptionsDTO,
     * or with status 400 (Bad Request) if the loginOptionsDTO is not valid,
     * or with status 500 (Internal Server Error) if the loginOptionsDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/login-options")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<LoginOptions> updateLoginOptions(@Valid @RequestBody LoginOptions loginOptions) throws URISyntaxException {
        log.debug("REST request to update LoginOptions : {}", loginOptions);
        if (loginOptions.getId() == null) {
            return createLoginOptions(loginOptions);
        }
        LoginOptions result = loginOptionsService.save(loginOptions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loginOptions.getId().toString()))
=======
    public ResponseEntity<LoginOptionsDTO> updateLoginOptions(@Valid @RequestBody LoginOptionsDTO loginOptionsDTO) throws URISyntaxException {
        log.debug("REST request to update LoginOptions : {}", loginOptionsDTO);
        if (loginOptionsDTO.getId() == null) {
            return createLoginOptions(loginOptionsDTO);
        }
        LoginOptionsDTO result = loginOptionsService.save(loginOptionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loginOptionsDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /login-options : get all the loginOptions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of loginOptions in body
     */
    @GetMapping("/login-options")
    @Timed
<<<<<<< HEAD
    public List<LoginOptions> getAllLoginOptions() {
=======
    public List<LoginOptionsDTO> getAllLoginOptions() {
>>>>>>> with_entities
        log.debug("REST request to get all LoginOptions");
        return loginOptionsService.findAll();
    }

    /**
     * GET  /login-options/:id : get the "id" loginOptions.
     *
<<<<<<< HEAD
     * @param id the id of the loginOptions to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loginOptions, or with status 404 (Not Found)
     */
    @GetMapping("/login-options/{id}")
    @Timed
    public ResponseEntity<LoginOptions> getLoginOptions(@PathVariable Long id) {
        log.debug("REST request to get LoginOptions : {}", id);
        LoginOptions loginOptions = loginOptionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(loginOptions));
=======
     * @param id the id of the loginOptionsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loginOptionsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/login-options/{id}")
    @Timed
    public ResponseEntity<LoginOptionsDTO> getLoginOptions(@PathVariable Long id) {
        log.debug("REST request to get LoginOptions : {}", id);
        LoginOptionsDTO loginOptionsDTO = loginOptionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(loginOptionsDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /login-options/:id : delete the "id" loginOptions.
     *
<<<<<<< HEAD
     * @param id the id of the loginOptions to delete
=======
     * @param id the id of the loginOptionsDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/login-options/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoginOptions(@PathVariable Long id) {
        log.debug("REST request to delete LoginOptions : {}", id);
        loginOptionsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/login-options?query=:query : search for the loginOptions corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the loginOptions search
=======
     * @param query the query of the loginOptions search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/login-options")
    @Timed
<<<<<<< HEAD
    public List<LoginOptions> searchLoginOptions(@RequestParam String query) {
=======
    public List<LoginOptionsDTO> searchLoginOptions(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search LoginOptions for query {}", query);
        return loginOptionsService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

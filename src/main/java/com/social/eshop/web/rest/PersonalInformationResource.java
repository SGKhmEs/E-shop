package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.service.PersonalInformationService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.PersonalInformationService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.PersonalInformationDTO;
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
 * REST controller for managing PersonalInformation.
 */
@RestController
@RequestMapping("/api")
public class PersonalInformationResource {

    private final Logger log = LoggerFactory.getLogger(PersonalInformationResource.class);

    private static final String ENTITY_NAME = "personalInformation";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final PersonalInformationService personalInformationService;

    public PersonalInformationResource(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    /**
     * POST  /personal-informations : Create a new personalInformation.
     *
<<<<<<< HEAD
     * @param personalInformation the personalInformation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personalInformation, or with status 400 (Bad Request) if the personalInformation has already an ID
=======
     * @param personalInformationDTO the personalInformationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personalInformationDTO, or with status 400 (Bad Request) if the personalInformation has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/personal-informations")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<PersonalInformation> createPersonalInformation(@Valid @RequestBody PersonalInformation personalInformation) throws URISyntaxException {
        log.debug("REST request to save PersonalInformation : {}", personalInformation);
        if (personalInformation.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new personalInformation cannot already have an ID")).body(null);
        }
        PersonalInformation result = personalInformationService.save(personalInformation);
=======
    public ResponseEntity<PersonalInformationDTO> createPersonalInformation(@Valid @RequestBody PersonalInformationDTO personalInformationDTO) throws URISyntaxException {
        log.debug("REST request to save PersonalInformation : {}", personalInformationDTO);
        if (personalInformationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new personalInformation cannot already have an ID")).body(null);
        }
        PersonalInformationDTO result = personalInformationService.save(personalInformationDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/personal-informations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /personal-informations : Updates an existing personalInformation.
     *
<<<<<<< HEAD
     * @param personalInformation the personalInformation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personalInformation,
     * or with status 400 (Bad Request) if the personalInformation is not valid,
     * or with status 500 (Internal Server Error) if the personalInformation couldn't be updated
=======
     * @param personalInformationDTO the personalInformationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personalInformationDTO,
     * or with status 400 (Bad Request) if the personalInformationDTO is not valid,
     * or with status 500 (Internal Server Error) if the personalInformationDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/personal-informations")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<PersonalInformation> updatePersonalInformation(@Valid @RequestBody PersonalInformation personalInformation) throws URISyntaxException {
        log.debug("REST request to update PersonalInformation : {}", personalInformation);
        if (personalInformation.getId() == null) {
            return createPersonalInformation(personalInformation);
        }
        PersonalInformation result = personalInformationService.save(personalInformation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personalInformation.getId().toString()))
=======
    public ResponseEntity<PersonalInformationDTO> updatePersonalInformation(@Valid @RequestBody PersonalInformationDTO personalInformationDTO) throws URISyntaxException {
        log.debug("REST request to update PersonalInformation : {}", personalInformationDTO);
        if (personalInformationDTO.getId() == null) {
            return createPersonalInformation(personalInformationDTO);
        }
        PersonalInformationDTO result = personalInformationService.save(personalInformationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personalInformationDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /personal-informations : get all the personalInformations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of personalInformations in body
     */
    @GetMapping("/personal-informations")
    @Timed
<<<<<<< HEAD
    public List<PersonalInformation> getAllPersonalInformations() {
=======
    public List<PersonalInformationDTO> getAllPersonalInformations() {
>>>>>>> with_entities
        log.debug("REST request to get all PersonalInformations");
        return personalInformationService.findAll();
    }

    /**
     * GET  /personal-informations/:id : get the "id" personalInformation.
     *
<<<<<<< HEAD
     * @param id the id of the personalInformation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personalInformation, or with status 404 (Not Found)
     */
    @GetMapping("/personal-informations/{id}")
    @Timed
    public ResponseEntity<PersonalInformation> getPersonalInformation(@PathVariable Long id) {
        log.debug("REST request to get PersonalInformation : {}", id);
        PersonalInformation personalInformation = personalInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(personalInformation));
=======
     * @param id the id of the personalInformationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personalInformationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/personal-informations/{id}")
    @Timed
    public ResponseEntity<PersonalInformationDTO> getPersonalInformation(@PathVariable Long id) {
        log.debug("REST request to get PersonalInformation : {}", id);
        PersonalInformationDTO personalInformationDTO = personalInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(personalInformationDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /personal-informations/:id : delete the "id" personalInformation.
     *
<<<<<<< HEAD
     * @param id the id of the personalInformation to delete
=======
     * @param id the id of the personalInformationDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/personal-informations/{id}")
    @Timed
    public ResponseEntity<Void> deletePersonalInformation(@PathVariable Long id) {
        log.debug("REST request to delete PersonalInformation : {}", id);
        personalInformationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/personal-informations?query=:query : search for the personalInformation corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the personalInformation search
=======
     * @param query the query of the personalInformation search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/personal-informations")
    @Timed
<<<<<<< HEAD
    public List<PersonalInformation> searchPersonalInformations(@RequestParam String query) {
=======
    public List<PersonalInformationDTO> searchPersonalInformations(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search PersonalInformations for query {}", query);
        return personalInformationService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

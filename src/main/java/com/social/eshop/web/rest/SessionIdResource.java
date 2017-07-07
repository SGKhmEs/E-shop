package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.SessionIdService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.SessionIdDTO;
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
 * REST controller for managing SessionId.
 */
@RestController
@RequestMapping("/api")
public class SessionIdResource {

    private final Logger log = LoggerFactory.getLogger(SessionIdResource.class);

    private static final String ENTITY_NAME = "sessionId";
        
    private final SessionIdService sessionIdService;

    public SessionIdResource(SessionIdService sessionIdService) {
        this.sessionIdService = sessionIdService;
    }

    /**
     * POST  /session-ids : Create a new sessionId.
     *
     * @param sessionIdDTO the sessionIdDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sessionIdDTO, or with status 400 (Bad Request) if the sessionId has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/session-ids")
    @Timed
    public ResponseEntity<SessionIdDTO> createSessionId(@RequestBody SessionIdDTO sessionIdDTO) throws URISyntaxException {
        log.debug("REST request to save SessionId : {}", sessionIdDTO);
        if (sessionIdDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sessionId cannot already have an ID")).body(null);
        }
        SessionIdDTO result = sessionIdService.save(sessionIdDTO);
        return ResponseEntity.created(new URI("/api/session-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /session-ids : Updates an existing sessionId.
     *
     * @param sessionIdDTO the sessionIdDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sessionIdDTO,
     * or with status 400 (Bad Request) if the sessionIdDTO is not valid,
     * or with status 500 (Internal Server Error) if the sessionIdDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/session-ids")
    @Timed
    public ResponseEntity<SessionIdDTO> updateSessionId(@RequestBody SessionIdDTO sessionIdDTO) throws URISyntaxException {
        log.debug("REST request to update SessionId : {}", sessionIdDTO);
        if (sessionIdDTO.getId() == null) {
            return createSessionId(sessionIdDTO);
        }
        SessionIdDTO result = sessionIdService.save(sessionIdDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sessionIdDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /session-ids : get all the sessionIds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sessionIds in body
     */
    @GetMapping("/session-ids")
    @Timed
    public List<SessionIdDTO> getAllSessionIds() {
        log.debug("REST request to get all SessionIds");
        return sessionIdService.findAll();
    }

    /**
     * GET  /session-ids/:id : get the "id" sessionId.
     *
     * @param id the id of the sessionIdDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sessionIdDTO, or with status 404 (Not Found)
     */
    @GetMapping("/session-ids/{id}")
    @Timed
    public ResponseEntity<SessionIdDTO> getSessionId(@PathVariable Long id) {
        log.debug("REST request to get SessionId : {}", id);
        SessionIdDTO sessionIdDTO = sessionIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sessionIdDTO));
    }

    /**
     * DELETE  /session-ids/:id : delete the "id" sessionId.
     *
     * @param id the id of the sessionIdDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/session-ids/{id}")
    @Timed
    public ResponseEntity<Void> deleteSessionId(@PathVariable Long id) {
        log.debug("REST request to delete SessionId : {}", id);
        sessionIdService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/session-ids?query=:query : search for the sessionId corresponding
     * to the query.
     *
     * @param query the query of the sessionId search 
     * @return the result of the search
     */
    @GetMapping("/_search/session-ids")
    @Timed
    public List<SessionIdDTO> searchSessionIds(@RequestParam String query) {
        log.debug("REST request to search SessionIds for query {}", query);
        return sessionIdService.search(query);
    }


}

package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.StaticPagesService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.StaticPagesDTO;
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
 * REST controller for managing StaticPages.
 */
@RestController
@RequestMapping("/api")
public class StaticPagesResource {

    private final Logger log = LoggerFactory.getLogger(StaticPagesResource.class);

    private static final String ENTITY_NAME = "staticPages";

    private final StaticPagesService staticPagesService;

    public StaticPagesResource(StaticPagesService staticPagesService) {
        this.staticPagesService = staticPagesService;
    }

    /**
     * POST  /static-pages : Create a new staticPages.
     *
     * @param staticPagesDTO the staticPagesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new staticPagesDTO, or with status 400 (Bad Request) if the staticPages has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/static-pages")
    @Timed
    public ResponseEntity<StaticPagesDTO> createStaticPages(@RequestBody StaticPagesDTO staticPagesDTO) throws URISyntaxException {
        log.debug("REST request to save StaticPages : {}", staticPagesDTO);
        if (staticPagesDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new staticPages cannot already have an ID")).body(null);
        }
        StaticPagesDTO result = staticPagesService.save(staticPagesDTO);
        return ResponseEntity.created(new URI("/api/static-pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /static-pages : Updates an existing staticPages.
     *
     * @param staticPagesDTO the staticPagesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated staticPagesDTO,
     * or with status 400 (Bad Request) if the staticPagesDTO is not valid,
     * or with status 500 (Internal Server Error) if the staticPagesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/static-pages")
    @Timed
    public ResponseEntity<StaticPagesDTO> updateStaticPages(@RequestBody StaticPagesDTO staticPagesDTO) throws URISyntaxException {
        log.debug("REST request to update StaticPages : {}", staticPagesDTO);
        if (staticPagesDTO.getId() == null) {
            return createStaticPages(staticPagesDTO);
        }
        StaticPagesDTO result = staticPagesService.save(staticPagesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, staticPagesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /static-pages : get all the staticPages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of staticPages in body
     */
    @GetMapping("/static-pages")
    @Timed
    public List<StaticPagesDTO> getAllStaticPages() {
        log.debug("REST request to get all StaticPages");
        return staticPagesService.findAll();
    }

    /**
     * GET  /static-pages/:id : get the "id" staticPages.
     *
     * @param id the id of the staticPagesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the staticPagesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/static-pages/{id}")
    @Timed
    public ResponseEntity<StaticPagesDTO> getStaticPages(@PathVariable Long id) {
        log.debug("REST request to get StaticPages : {}", id);
        StaticPagesDTO staticPagesDTO = staticPagesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(staticPagesDTO));
    }

    /**
     * DELETE  /static-pages/:id : delete the "id" staticPages.
     *
     * @param id the id of the staticPagesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/static-pages/{id}")
    @Timed
    public ResponseEntity<Void> deleteStaticPages(@PathVariable Long id) {
        log.debug("REST request to delete StaticPages : {}", id);
        staticPagesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/static-pages?query=:query : search for the staticPages corresponding
     * to the query.
     *
     * @param query the query of the staticPages search
     * @return the result of the search
     */
    @GetMapping("/_search/static-pages")
    @Timed
    public List<StaticPagesDTO> searchStaticPages(@RequestParam String query) {
        log.debug("REST request to search StaticPages for query {}", query);
        return staticPagesService.search(query);
    }

}

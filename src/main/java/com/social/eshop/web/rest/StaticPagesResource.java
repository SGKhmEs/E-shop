package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.domain.StaticPages;
import com.social.eshop.service.StaticPagesService;
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
     * @param staticPages the staticPages to create
     * @return the ResponseEntity with status 201 (Created) and with body the new staticPages, or with status 400 (Bad Request) if the staticPages has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/static-pages")
    @Timed
    public ResponseEntity<StaticPages> createStaticPages(@RequestBody StaticPages staticPages) throws URISyntaxException {
        log.debug("REST request to save StaticPages : {}", staticPages);
        if (staticPages.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new staticPages cannot already have an ID")).body(null);
        }
        StaticPages result = staticPagesService.save(staticPages);
        return ResponseEntity.created(new URI("/api/static-pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /static-pages : Updates an existing staticPages.
     *
     * @param staticPages the staticPages to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated staticPages,
     * or with status 400 (Bad Request) if the staticPages is not valid,
     * or with status 500 (Internal Server Error) if the staticPages couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/static-pages")
    @Timed
    public ResponseEntity<StaticPages> updateStaticPages(@RequestBody StaticPages staticPages) throws URISyntaxException {
        log.debug("REST request to update StaticPages : {}", staticPages);
        if (staticPages.getId() == null) {
            return createStaticPages(staticPages);
        }
        StaticPages result = staticPagesService.save(staticPages);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, staticPages.getId().toString()))
            .body(result);
    }

    /**
     * GET  /static-pages : get all the staticPages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of staticPages in body
     */
    @GetMapping("/static-pages")
    @Timed
    public List<StaticPages> getAllStaticPages() {
        log.debug("REST request to get all StaticPages");
        return staticPagesService.findAll();
    }

    /**
     * GET  /static-pages/:id : get the "id" staticPages.
     *
     * @param id the id of the staticPages to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the staticPages, or with status 404 (Not Found)
     */
    @GetMapping("/static-pages/{id}")
    @Timed
    public ResponseEntity<StaticPages> getStaticPages(@PathVariable Long id) {
        log.debug("REST request to get StaticPages : {}", id);
        StaticPages staticPages = staticPagesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(staticPages));
    }

    /**
     * DELETE  /static-pages/:id : delete the "id" staticPages.
     *
     * @param id the id of the staticPages to delete
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
    public List<StaticPages> searchStaticPages(@RequestParam String query) {
        log.debug("REST request to search StaticPages for query {}", query);
        return staticPagesService.search(query);
    }

}

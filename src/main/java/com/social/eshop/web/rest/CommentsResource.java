package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.CommentsService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.CommentsDTO;
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
 * REST controller for managing Comments.
 */
@RestController
@RequestMapping("/api")
public class CommentsResource {

    private final Logger log = LoggerFactory.getLogger(CommentsResource.class);

    private static final String ENTITY_NAME = "comments";

    private final CommentsService commentsService;

    public CommentsResource(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    /**
     * POST  /comments : Create a new comments.
     *
     * @param commentsDTO the commentsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commentsDTO, or with status 400 (Bad Request) if the comments has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comments")
    @Timed
    public ResponseEntity<CommentsDTO> createComments(@RequestBody CommentsDTO commentsDTO) throws URISyntaxException {
        log.debug("REST request to save Comments : {}", commentsDTO);
        if (commentsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new comments cannot already have an ID")).body(null);
        }
        CommentsDTO result = commentsService.save(commentsDTO);
        return ResponseEntity.created(new URI("/api/comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comments : Updates an existing comments.
     *
     * @param commentsDTO the commentsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated commentsDTO,
     * or with status 400 (Bad Request) if the commentsDTO is not valid,
     * or with status 500 (Internal Server Error) if the commentsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comments")
    @Timed
    public ResponseEntity<CommentsDTO> updateComments(@RequestBody CommentsDTO commentsDTO) throws URISyntaxException {
        log.debug("REST request to update Comments : {}", commentsDTO);
        if (commentsDTO.getId() == null) {
            return createComments(commentsDTO);
        }
        CommentsDTO result = commentsService.save(commentsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, commentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comments : get all the comments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of comments in body
     */
    @GetMapping("/comments")
    @Timed
    public List<CommentsDTO> getAllComments() {
        log.debug("REST request to get all Comments");
        return commentsService.findAll();
    }

    /**
     * GET  /comments/:id : get the "id" comments.
     *
     * @param id the id of the commentsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commentsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comments/{id}")
    @Timed
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Long id) {
        log.debug("REST request to get Comments : {}", id);
        CommentsDTO commentsDTO = commentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(commentsDTO));
    }

    /**
     * DELETE  /comments/:id : delete the "id" comments.
     *
     * @param id the id of the commentsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comments/{id}")
    @Timed
    public ResponseEntity<Void> deleteComments(@PathVariable Long id) {
        log.debug("REST request to delete Comments : {}", id);
        commentsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/comments?query=:query : search for the comments corresponding
     * to the query.
     *
     * @param query the query of the comments search
     * @return the result of the search
     */
    @GetMapping("/_search/comments")
    @Timed
    public List<CommentsDTO> searchComments(@RequestParam String query) {
        log.debug("REST request to search Comments for query {}", query);
        return commentsService.search(query);
    }

}

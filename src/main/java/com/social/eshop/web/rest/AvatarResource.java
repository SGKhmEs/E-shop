package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.AvatarService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.AvatarDTO;
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
 * REST controller for managing Avatar.
 */
@RestController
@RequestMapping("/api")
public class AvatarResource {

    private final Logger log = LoggerFactory.getLogger(AvatarResource.class);

    private static final String ENTITY_NAME = "avatar";

    private final AvatarService avatarService;

    public AvatarResource(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    /**
     * POST  /avatars : Create a new avatar.
     *
     * @param avatarDTO the avatarDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new avatarDTO, or with status 400 (Bad Request) if the avatar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/avatars")
    @Timed
    public ResponseEntity<AvatarDTO> createAvatar(@RequestBody AvatarDTO avatarDTO) throws URISyntaxException {
        log.debug("REST request to save Avatar : {}", avatarDTO);
        if (avatarDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new avatar cannot already have an ID")).body(null);
        }
        AvatarDTO result = avatarService.save(avatarDTO);
        return ResponseEntity.created(new URI("/api/avatars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /avatars : Updates an existing avatar.
     *
     * @param avatarDTO the avatarDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated avatarDTO,
     * or with status 400 (Bad Request) if the avatarDTO is not valid,
     * or with status 500 (Internal Server Error) if the avatarDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/avatars")
    @Timed
    public ResponseEntity<AvatarDTO> updateAvatar(@RequestBody AvatarDTO avatarDTO) throws URISyntaxException {
        log.debug("REST request to update Avatar : {}", avatarDTO);
        if (avatarDTO.getId() == null) {
            return createAvatar(avatarDTO);
        }
        AvatarDTO result = avatarService.save(avatarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, avatarDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /avatars : get all the avatars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of avatars in body
     */
    @GetMapping("/avatars")
    @Timed
    public List<AvatarDTO> getAllAvatars() {
        log.debug("REST request to get all Avatars");
        return avatarService.findAll();
    }

    /**
     * GET  /avatars/:id : get the "id" avatar.
     *
     * @param id the id of the avatarDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the avatarDTO, or with status 404 (Not Found)
     */
    @GetMapping("/avatars/{id}")
    @Timed
    public ResponseEntity<AvatarDTO> getAvatar(@PathVariable Long id) {
        log.debug("REST request to get Avatar : {}", id);
        AvatarDTO avatarDTO = avatarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(avatarDTO));
    }

    /**
     * DELETE  /avatars/:id : delete the "id" avatar.
     *
     * @param id the id of the avatarDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/avatars/{id}")
    @Timed
    public ResponseEntity<Void> deleteAvatar(@PathVariable Long id) {
        log.debug("REST request to delete Avatar : {}", id);
        avatarService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/avatars?query=:query : search for the avatar corresponding
     * to the query.
     *
     * @param query the query of the avatar search
     * @return the result of the search
     */
    @GetMapping("/_search/avatars")
    @Timed
    public List<AvatarDTO> searchAvatars(@RequestParam String query) {
        log.debug("REST request to search Avatars for query {}", query);
        return avatarService.search(query);
    }

}

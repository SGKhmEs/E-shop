package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.WishListService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.WishListDTO;
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
 * REST controller for managing WishList.
 */
@RestController
@RequestMapping("/api")
public class WishListResource {

    private final Logger log = LoggerFactory.getLogger(WishListResource.class);

    private static final String ENTITY_NAME = "wishList";

    private final WishListService wishListService;

    public WishListResource(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    /**
     * POST  /wish-lists : Create a new wishList.
     *
     * @param wishListDTO the wishListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wishListDTO, or with status 400 (Bad Request) if the wishList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wish-lists")
    @Timed
    public ResponseEntity<WishListDTO> createWishList(@RequestBody WishListDTO wishListDTO) throws URISyntaxException {
        log.debug("REST request to save WishList : {}", wishListDTO);
        if (wishListDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wishList cannot already have an ID")).body(null);
        }
        WishListDTO result = wishListService.save(wishListDTO);
        return ResponseEntity.created(new URI("/api/wish-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wish-lists : Updates an existing wishList.
     *
     * @param wishListDTO the wishListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wishListDTO,
     * or with status 400 (Bad Request) if the wishListDTO is not valid,
     * or with status 500 (Internal Server Error) if the wishListDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wish-lists")
    @Timed
    public ResponseEntity<WishListDTO> updateWishList(@RequestBody WishListDTO wishListDTO) throws URISyntaxException {
        log.debug("REST request to update WishList : {}", wishListDTO);
        if (wishListDTO.getId() == null) {
            return createWishList(wishListDTO);
        }
        WishListDTO result = wishListService.save(wishListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wishListDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wish-lists : get all the wishLists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wishLists in body
     */
    @GetMapping("/wish-lists")
    @Timed
    public List<WishListDTO> getAllWishLists() {
        log.debug("REST request to get all WishLists");
        return wishListService.findAll();
    }

    /**
     * GET  /wish-lists/:id : get the "id" wishList.
     *
     * @param id the id of the wishListDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wishListDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wish-lists/{id}")
    @Timed
    public ResponseEntity<WishListDTO> getWishList(@PathVariable Long id) {
        log.debug("REST request to get WishList : {}", id);
        WishListDTO wishListDTO = wishListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wishListDTO));
    }

    /**
     * DELETE  /wish-lists/:id : delete the "id" wishList.
     *
     * @param id the id of the wishListDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wish-lists/{id}")
    @Timed
    public ResponseEntity<Void> deleteWishList(@PathVariable Long id) {
        log.debug("REST request to delete WishList : {}", id);
        wishListService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/wish-lists?query=:query : search for the wishList corresponding
     * to the query.
     *
     * @param query the query of the wishList search
     * @return the result of the search
     */
    @GetMapping("/_search/wish-lists")
    @Timed
    public List<WishListDTO> searchWishLists(@RequestParam String query) {
        log.debug("REST request to search WishLists for query {}", query);
        return wishListService.search(query);
    }

}

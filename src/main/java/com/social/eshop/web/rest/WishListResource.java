package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.WishList;
import com.social.eshop.service.WishListService;
import com.social.eshop.web.rest.util.HeaderUtil;
=======
import com.social.eshop.service.WishListService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.WishListDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final WishListService wishListService;

    public WishListResource(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    /**
     * POST  /wish-lists : Create a new wishList.
     *
<<<<<<< HEAD
     * @param wishList the wishList to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wishList, or with status 400 (Bad Request) if the wishList has already an ID
=======
     * @param wishListDTO the wishListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wishListDTO, or with status 400 (Bad Request) if the wishList has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wish-lists")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<WishList> createWishList(@RequestBody WishList wishList) throws URISyntaxException {
        log.debug("REST request to save WishList : {}", wishList);
        if (wishList.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wishList cannot already have an ID")).body(null);
        }
        WishList result = wishListService.save(wishList);
=======
    public ResponseEntity<WishListDTO> createWishList(@RequestBody WishListDTO wishListDTO) throws URISyntaxException {
        log.debug("REST request to save WishList : {}", wishListDTO);
        if (wishListDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wishList cannot already have an ID")).body(null);
        }
        WishListDTO result = wishListService.save(wishListDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/wish-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wish-lists : Updates an existing wishList.
     *
<<<<<<< HEAD
     * @param wishList the wishList to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wishList,
     * or with status 400 (Bad Request) if the wishList is not valid,
     * or with status 500 (Internal Server Error) if the wishList couldn't be updated
=======
     * @param wishListDTO the wishListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wishListDTO,
     * or with status 400 (Bad Request) if the wishListDTO is not valid,
     * or with status 500 (Internal Server Error) if the wishListDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wish-lists")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<WishList> updateWishList(@RequestBody WishList wishList) throws URISyntaxException {
        log.debug("REST request to update WishList : {}", wishList);
        if (wishList.getId() == null) {
            return createWishList(wishList);
        }
        WishList result = wishListService.save(wishList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wishList.getId().toString()))
=======
    public ResponseEntity<WishListDTO> updateWishList(@RequestBody WishListDTO wishListDTO) throws URISyntaxException {
        log.debug("REST request to update WishList : {}", wishListDTO);
        if (wishListDTO.getId() == null) {
            return createWishList(wishListDTO);
        }
        WishListDTO result = wishListService.save(wishListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wishListDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /wish-lists : get all the wishLists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wishLists in body
     */
    @GetMapping("/wish-lists")
    @Timed
<<<<<<< HEAD
    public List<WishList> getAllWishLists() {
=======
    public List<WishListDTO> getAllWishLists() {
>>>>>>> with_entities
        log.debug("REST request to get all WishLists");
        return wishListService.findAll();
    }

    /**
     * GET  /wish-lists/:id : get the "id" wishList.
     *
<<<<<<< HEAD
     * @param id the id of the wishList to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wishList, or with status 404 (Not Found)
     */
    @GetMapping("/wish-lists/{id}")
    @Timed
    public ResponseEntity<WishList> getWishList(@PathVariable Long id) {
        log.debug("REST request to get WishList : {}", id);
        WishList wishList = wishListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wishList));
=======
     * @param id the id of the wishListDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wishListDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wish-lists/{id}")
    @Timed
    public ResponseEntity<WishListDTO> getWishList(@PathVariable Long id) {
        log.debug("REST request to get WishList : {}", id);
        WishListDTO wishListDTO = wishListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wishListDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /wish-lists/:id : delete the "id" wishList.
     *
<<<<<<< HEAD
     * @param id the id of the wishList to delete
=======
     * @param id the id of the wishListDTO to delete
>>>>>>> with_entities
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
<<<<<<< HEAD
     * @param query the query of the wishList search
=======
     * @param query the query of the wishList search 
>>>>>>> with_entities
     * @return the result of the search
     */
    @GetMapping("/_search/wish-lists")
    @Timed
<<<<<<< HEAD
    public List<WishList> searchWishLists(@RequestParam String query) {
=======
    public List<WishListDTO> searchWishLists(@RequestParam String query) {
>>>>>>> with_entities
        log.debug("REST request to search WishLists for query {}", query);
        return wishListService.search(query);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

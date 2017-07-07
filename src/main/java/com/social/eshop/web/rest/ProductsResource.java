package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
import com.social.eshop.domain.Products;
import com.social.eshop.service.ProductsService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.web.rest.util.PaginationUtil;
=======
import com.social.eshop.service.ProductsService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.web.rest.util.PaginationUtil;
import com.social.eshop.service.dto.ProductsDTO;
>>>>>>> with_entities
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing Products.
 */
@RestController
@RequestMapping("/api")
public class ProductsResource {

    private final Logger log = LoggerFactory.getLogger(ProductsResource.class);

    private static final String ENTITY_NAME = "products";
<<<<<<< HEAD

=======
        
>>>>>>> with_entities
    private final ProductsService productsService;

    public ProductsResource(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * POST  /products : Create a new products.
     *
<<<<<<< HEAD
     * @param products the products to create
     * @return the ResponseEntity with status 201 (Created) and with body the new products, or with status 400 (Bad Request) if the products has already an ID
=======
     * @param productsDTO the productsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productsDTO, or with status 400 (Bad Request) if the products has already an ID
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/products")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Products> createProducts(@Valid @RequestBody Products products) throws URISyntaxException {
        log.debug("REST request to save Products : {}", products);
        if (products.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new products cannot already have an ID")).body(null);
        }
        Products result = productsService.save(products);
=======
    public ResponseEntity<ProductsDTO> createProducts(@Valid @RequestBody ProductsDTO productsDTO) throws URISyntaxException {
        log.debug("REST request to save Products : {}", productsDTO);
        if (productsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new products cannot already have an ID")).body(null);
        }
        ProductsDTO result = productsService.save(productsDTO);
>>>>>>> with_entities
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /products : Updates an existing products.
     *
<<<<<<< HEAD
     * @param products the products to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated products,
     * or with status 400 (Bad Request) if the products is not valid,
     * or with status 500 (Internal Server Error) if the products couldn't be updated
=======
     * @param productsDTO the productsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productsDTO,
     * or with status 400 (Bad Request) if the productsDTO is not valid,
     * or with status 500 (Internal Server Error) if the productsDTO couldnt be updated
>>>>>>> with_entities
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/products")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<Products> updateProducts(@Valid @RequestBody Products products) throws URISyntaxException {
        log.debug("REST request to update Products : {}", products);
        if (products.getId() == null) {
            return createProducts(products);
        }
        Products result = productsService.save(products);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, products.getId().toString()))
=======
    public ResponseEntity<ProductsDTO> updateProducts(@Valid @RequestBody ProductsDTO productsDTO) throws URISyntaxException {
        log.debug("REST request to update Products : {}", productsDTO);
        if (productsDTO.getId() == null) {
            return createProducts(productsDTO);
        }
        ProductsDTO result = productsService.save(productsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productsDTO.getId().toString()))
>>>>>>> with_entities
            .body(result);
    }

    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<List<Products>> getAllProducts(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<Products> page = productsService.findAll(pageable);
=======
    public ResponseEntity<List<ProductsDTO>> getAllProducts(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<ProductsDTO> page = productsService.findAll(pageable);
>>>>>>> with_entities
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /products/:id : get the "id" products.
     *
<<<<<<< HEAD
     * @param id the id of the products to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the products, or with status 404 (Not Found)
     */
    @GetMapping("/products/{id}")
    @Timed
    public ResponseEntity<Products> getProducts(@PathVariable Long id) {
        log.debug("REST request to get Products : {}", id);
        Products products = productsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(products));
=======
     * @param id the id of the productsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/{id}")
    @Timed
    public ResponseEntity<ProductsDTO> getProducts(@PathVariable Long id) {
        log.debug("REST request to get Products : {}", id);
        ProductsDTO productsDTO = productsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productsDTO));
>>>>>>> with_entities
    }

    /**
     * DELETE  /products/:id : delete the "id" products.
     *
<<<<<<< HEAD
     * @param id the id of the products to delete
=======
     * @param id the id of the productsDTO to delete
>>>>>>> with_entities
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/products/{id}")
    @Timed
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
        log.debug("REST request to delete Products : {}", id);
        productsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/products?query=:query : search for the products corresponding
     * to the query.
     *
<<<<<<< HEAD
     * @param query the query of the products search
=======
     * @param query the query of the products search 
>>>>>>> with_entities
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/products")
    @Timed
<<<<<<< HEAD
    public ResponseEntity<List<Products>> searchProducts(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Products for query {}", query);
        Page<Products> page = productsService.search(query, pageable);
=======
    public ResponseEntity<List<ProductsDTO>> searchProducts(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Products for query {}", query);
        Page<ProductsDTO> page = productsService.search(query, pageable);
>>>>>>> with_entities
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

<<<<<<< HEAD
=======

>>>>>>> with_entities
}

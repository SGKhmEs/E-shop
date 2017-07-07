package com.social.eshop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.social.eshop.service.HistoryOrderService;
import com.social.eshop.web.rest.util.HeaderUtil;
import com.social.eshop.service.dto.HistoryOrderDTO;
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
 * REST controller for managing HistoryOrder.
 */
@RestController
@RequestMapping("/api")
public class HistoryOrderResource {

    private final Logger log = LoggerFactory.getLogger(HistoryOrderResource.class);

    private static final String ENTITY_NAME = "historyOrder";
        
    private final HistoryOrderService historyOrderService;

    public HistoryOrderResource(HistoryOrderService historyOrderService) {
        this.historyOrderService = historyOrderService;
    }

    /**
     * POST  /history-orders : Create a new historyOrder.
     *
     * @param historyOrderDTO the historyOrderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new historyOrderDTO, or with status 400 (Bad Request) if the historyOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/history-orders")
    @Timed
    public ResponseEntity<HistoryOrderDTO> createHistoryOrder(@RequestBody HistoryOrderDTO historyOrderDTO) throws URISyntaxException {
        log.debug("REST request to save HistoryOrder : {}", historyOrderDTO);
        if (historyOrderDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new historyOrder cannot already have an ID")).body(null);
        }
        HistoryOrderDTO result = historyOrderService.save(historyOrderDTO);
        return ResponseEntity.created(new URI("/api/history-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /history-orders : Updates an existing historyOrder.
     *
     * @param historyOrderDTO the historyOrderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated historyOrderDTO,
     * or with status 400 (Bad Request) if the historyOrderDTO is not valid,
     * or with status 500 (Internal Server Error) if the historyOrderDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/history-orders")
    @Timed
    public ResponseEntity<HistoryOrderDTO> updateHistoryOrder(@RequestBody HistoryOrderDTO historyOrderDTO) throws URISyntaxException {
        log.debug("REST request to update HistoryOrder : {}", historyOrderDTO);
        if (historyOrderDTO.getId() == null) {
            return createHistoryOrder(historyOrderDTO);
        }
        HistoryOrderDTO result = historyOrderService.save(historyOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, historyOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /history-orders : get all the historyOrders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of historyOrders in body
     */
    @GetMapping("/history-orders")
    @Timed
    public List<HistoryOrderDTO> getAllHistoryOrders() {
        log.debug("REST request to get all HistoryOrders");
        return historyOrderService.findAll();
    }

    /**
     * GET  /history-orders/:id : get the "id" historyOrder.
     *
     * @param id the id of the historyOrderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historyOrderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/history-orders/{id}")
    @Timed
    public ResponseEntity<HistoryOrderDTO> getHistoryOrder(@PathVariable Long id) {
        log.debug("REST request to get HistoryOrder : {}", id);
        HistoryOrderDTO historyOrderDTO = historyOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(historyOrderDTO));
    }

    /**
     * DELETE  /history-orders/:id : delete the "id" historyOrder.
     *
     * @param id the id of the historyOrderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/history-orders/{id}")
    @Timed
    public ResponseEntity<Void> deleteHistoryOrder(@PathVariable Long id) {
        log.debug("REST request to delete HistoryOrder : {}", id);
        historyOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/history-orders?query=:query : search for the historyOrder corresponding
     * to the query.
     *
     * @param query the query of the historyOrder search 
     * @return the result of the search
     */
    @GetMapping("/_search/history-orders")
    @Timed
    public List<HistoryOrderDTO> searchHistoryOrders(@RequestParam String query) {
        log.debug("REST request to search HistoryOrders for query {}", query);
        return historyOrderService.search(query);
    }


}

package com.social.eshop.service;

import com.social.eshop.service.dto.HistoryOrderDTO;
import java.util.List;

/**
 * Service Interface for managing HistoryOrder.
 */
public interface HistoryOrderService {

    /**
     * Save a historyOrder.
     *
     * @param historyOrderDTO the entity to save
     * @return the persisted entity
     */
    HistoryOrderDTO save(HistoryOrderDTO historyOrderDTO);

    /**
     *  Get all the historyOrders.
     *  
     *  @return the list of entities
     */
    List<HistoryOrderDTO> findAll();

    /**
     *  Get the "id" historyOrder.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    HistoryOrderDTO findOne(Long id);

    /**
     *  Delete the "id" historyOrder.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the historyOrder corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<HistoryOrderDTO> search(String query);
}

package com.social.eshop.service.impl;

import com.social.eshop.service.HistoryOrderService;
import com.social.eshop.domain.HistoryOrder;
import com.social.eshop.repository.HistoryOrderRepository;
import com.social.eshop.repository.search.HistoryOrderSearchRepository;
import com.social.eshop.service.dto.HistoryOrderDTO;
import com.social.eshop.service.mapper.HistoryOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing HistoryOrder.
 */
@Service
@Transactional
public class HistoryOrderServiceImpl implements HistoryOrderService{

    private final Logger log = LoggerFactory.getLogger(HistoryOrderServiceImpl.class);
    
    private final HistoryOrderRepository historyOrderRepository;

    private final HistoryOrderMapper historyOrderMapper;

    private final HistoryOrderSearchRepository historyOrderSearchRepository;

    public HistoryOrderServiceImpl(HistoryOrderRepository historyOrderRepository, HistoryOrderMapper historyOrderMapper, HistoryOrderSearchRepository historyOrderSearchRepository) {
        this.historyOrderRepository = historyOrderRepository;
        this.historyOrderMapper = historyOrderMapper;
        this.historyOrderSearchRepository = historyOrderSearchRepository;
    }

    /**
     * Save a historyOrder.
     *
     * @param historyOrderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HistoryOrderDTO save(HistoryOrderDTO historyOrderDTO) {
        log.debug("Request to save HistoryOrder : {}", historyOrderDTO);
        HistoryOrder historyOrder = historyOrderMapper.toEntity(historyOrderDTO);
        historyOrder = historyOrderRepository.save(historyOrder);
        HistoryOrderDTO result = historyOrderMapper.toDto(historyOrder);
        historyOrderSearchRepository.save(historyOrder);
        return result;
    }

    /**
     *  Get all the historyOrders.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HistoryOrderDTO> findAll() {
        log.debug("Request to get all HistoryOrders");
        List<HistoryOrderDTO> result = historyOrderRepository.findAll().stream()
            .map(historyOrderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one historyOrder by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HistoryOrderDTO findOne(Long id) {
        log.debug("Request to get HistoryOrder : {}", id);
        HistoryOrder historyOrder = historyOrderRepository.findOne(id);
        HistoryOrderDTO historyOrderDTO = historyOrderMapper.toDto(historyOrder);
        return historyOrderDTO;
    }

    /**
     *  Delete the  historyOrder by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistoryOrder : {}", id);
        historyOrderRepository.delete(id);
        historyOrderSearchRepository.delete(id);
    }

    /**
     * Search for the historyOrder corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HistoryOrderDTO> search(String query) {
        log.debug("Request to search HistoryOrders for query {}", query);
        return StreamSupport
            .stream(historyOrderSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(historyOrderMapper::toDto)
            .collect(Collectors.toList());
    }
}

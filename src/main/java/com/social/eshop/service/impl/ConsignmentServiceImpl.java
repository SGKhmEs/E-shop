package com.social.eshop.service.impl;

import com.social.eshop.service.ConsignmentService;
import com.social.eshop.domain.Consignment;
import com.social.eshop.repository.ConsignmentRepository;
import com.social.eshop.repository.search.ConsignmentSearchRepository;
import com.social.eshop.service.dto.ConsignmentDTO;
import com.social.eshop.service.mapper.ConsignmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Consignment.
 */
@Service
@Transactional
public class ConsignmentServiceImpl implements ConsignmentService{

    private final Logger log = LoggerFactory.getLogger(ConsignmentServiceImpl.class);
    
    private final ConsignmentRepository consignmentRepository;

    private final ConsignmentMapper consignmentMapper;

    private final ConsignmentSearchRepository consignmentSearchRepository;

    public ConsignmentServiceImpl(ConsignmentRepository consignmentRepository, ConsignmentMapper consignmentMapper, ConsignmentSearchRepository consignmentSearchRepository) {
        this.consignmentRepository = consignmentRepository;
        this.consignmentMapper = consignmentMapper;
        this.consignmentSearchRepository = consignmentSearchRepository;
    }

    /**
     * Save a consignment.
     *
     * @param consignmentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsignmentDTO save(ConsignmentDTO consignmentDTO) {
        log.debug("Request to save Consignment : {}", consignmentDTO);
        Consignment consignment = consignmentMapper.toEntity(consignmentDTO);
        consignment = consignmentRepository.save(consignment);
        ConsignmentDTO result = consignmentMapper.toDto(consignment);
        consignmentSearchRepository.save(consignment);
        return result;
    }

    /**
     *  Get all the consignments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConsignmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Consignments");
        Page<Consignment> result = consignmentRepository.findAll(pageable);
        return result.map(consignment -> consignmentMapper.toDto(consignment));
    }

    /**
     *  Get one consignment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConsignmentDTO findOne(Long id) {
        log.debug("Request to get Consignment : {}", id);
        Consignment consignment = consignmentRepository.findOne(id);
        ConsignmentDTO consignmentDTO = consignmentMapper.toDto(consignment);
        return consignmentDTO;
    }

    /**
     *  Delete the  consignment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Consignment : {}", id);
        consignmentRepository.delete(id);
        consignmentSearchRepository.delete(id);
    }

    /**
     * Search for the consignment corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConsignmentDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Consignments for query {}", query);
        Page<Consignment> result = consignmentSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(consignment -> consignmentMapper.toDto(consignment));
    }
}

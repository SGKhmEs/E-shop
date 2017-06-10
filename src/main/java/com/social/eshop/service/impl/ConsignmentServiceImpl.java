package com.social.eshop.service.impl;

import com.social.eshop.service.ConsignmentService;
import com.social.eshop.domain.Consignment;
import com.social.eshop.repository.ConsignmentRepository;
import com.social.eshop.repository.search.ConsignmentSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Consignment.
 */
@Service
@Transactional
public class ConsignmentServiceImpl implements ConsignmentService{

    private final Logger log = LoggerFactory.getLogger(ConsignmentServiceImpl.class);

    private final ConsignmentRepository consignmentRepository;

    private final ConsignmentSearchRepository consignmentSearchRepository;

    public ConsignmentServiceImpl(ConsignmentRepository consignmentRepository, ConsignmentSearchRepository consignmentSearchRepository) {
        this.consignmentRepository = consignmentRepository;
        this.consignmentSearchRepository = consignmentSearchRepository;
    }

    /**
     * Save a consignment.
     *
     * @param consignment the entity to save
     * @return the persisted entity
     */
    @Override
    public Consignment save(Consignment consignment) {
        log.debug("Request to save Consignment : {}", consignment);
        Consignment result = consignmentRepository.save(consignment);
        consignmentSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the consignments.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Consignment> findAll() {
        log.debug("Request to get all Consignments");
        return consignmentRepository.findAll();
    }

    /**
     *  Get one consignment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Consignment findOne(Long id) {
        log.debug("Request to get Consignment : {}", id);
        return consignmentRepository.findOne(id);
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
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Consignment> search(String query) {
        log.debug("Request to search Consignments for query {}", query);
        return StreamSupport
            .stream(consignmentSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

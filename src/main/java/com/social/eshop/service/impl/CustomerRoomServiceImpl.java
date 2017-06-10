package com.social.eshop.service.impl;

import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.domain.CustomerRoom;
import com.social.eshop.repository.CustomerRoomRepository;
import com.social.eshop.repository.search.CustomerRoomSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerRoom.
 */
@Service
@Transactional
public class CustomerRoomServiceImpl implements CustomerRoomService{

    private final Logger log = LoggerFactory.getLogger(CustomerRoomServiceImpl.class);

    private final CustomerRoomRepository customerRoomRepository;

    private final CustomerRoomSearchRepository customerRoomSearchRepository;

    public CustomerRoomServiceImpl(CustomerRoomRepository customerRoomRepository, CustomerRoomSearchRepository customerRoomSearchRepository) {
        this.customerRoomRepository = customerRoomRepository;
        this.customerRoomSearchRepository = customerRoomSearchRepository;
    }

    /**
     * Save a customerRoom.
     *
     * @param customerRoom the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerRoom save(CustomerRoom customerRoom) {
        log.debug("Request to save CustomerRoom : {}", customerRoom);
        CustomerRoom result = customerRoomRepository.save(customerRoom);
        customerRoomSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the customerRooms.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerRoom> findAll() {
        log.debug("Request to get all CustomerRooms");
        return customerRoomRepository.findAll();
    }

    /**
     *  Get one customerRoom by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerRoom findOne(Long id) {
        log.debug("Request to get CustomerRoom : {}", id);
        return customerRoomRepository.findOne(id);
    }

    /**
     *  Delete the  customerRoom by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerRoom : {}", id);
        customerRoomRepository.delete(id);
        customerRoomSearchRepository.delete(id);
    }

    /**
     * Search for the customerRoom corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerRoom> search(String query) {
        log.debug("Request to search CustomerRooms for query {}", query);
        return StreamSupport
            .stream(customerRoomSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

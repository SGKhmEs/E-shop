package com.social.eshop.service.impl;

import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.domain.CustomerRoom;
import com.social.eshop.repository.CustomerRoomRepository;
import com.social.eshop.repository.search.CustomerRoomSearchRepository;
import com.social.eshop.service.dto.CustomerRoomDTO;
import com.social.eshop.service.mapper.CustomerRoomMapper;
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
 * Service Implementation for managing CustomerRoom.
 */
@Service
@Transactional
public class CustomerRoomServiceImpl implements CustomerRoomService{

    private final Logger log = LoggerFactory.getLogger(CustomerRoomServiceImpl.class);
    
    private final CustomerRoomRepository customerRoomRepository;

    private final CustomerRoomMapper customerRoomMapper;

    private final CustomerRoomSearchRepository customerRoomSearchRepository;

    public CustomerRoomServiceImpl(CustomerRoomRepository customerRoomRepository, CustomerRoomMapper customerRoomMapper, CustomerRoomSearchRepository customerRoomSearchRepository) {
        this.customerRoomRepository = customerRoomRepository;
        this.customerRoomMapper = customerRoomMapper;
        this.customerRoomSearchRepository = customerRoomSearchRepository;
    }

    /**
     * Save a customerRoom.
     *
     * @param customerRoomDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerRoomDTO save(CustomerRoomDTO customerRoomDTO) {
        log.debug("Request to save CustomerRoom : {}", customerRoomDTO);
        CustomerRoom customerRoom = customerRoomMapper.toEntity(customerRoomDTO);
        customerRoom = customerRoomRepository.save(customerRoom);
        CustomerRoomDTO result = customerRoomMapper.toDto(customerRoom);
        customerRoomSearchRepository.save(customerRoom);
        return result;
    }

    /**
     *  Get all the customerRooms.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerRoomDTO> findAll() {
        log.debug("Request to get all CustomerRooms");
        List<CustomerRoomDTO> result = customerRoomRepository.findAll().stream()
            .map(customerRoomMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one customerRoom by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerRoomDTO findOne(Long id) {
        log.debug("Request to get CustomerRoom : {}", id);
        CustomerRoom customerRoom = customerRoomRepository.findOne(id);
        CustomerRoomDTO customerRoomDTO = customerRoomMapper.toDto(customerRoom);
        return customerRoomDTO;
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
    public List<CustomerRoomDTO> search(String query) {
        log.debug("Request to search CustomerRooms for query {}", query);
        return StreamSupport
            .stream(customerRoomSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerRoomMapper::toDto)
            .collect(Collectors.toList());
    }
}

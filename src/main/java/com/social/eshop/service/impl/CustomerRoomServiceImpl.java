package com.social.eshop.service.impl;

import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.domain.CustomerRoom;
import com.social.eshop.repository.CustomerRoomRepository;
import com.social.eshop.repository.search.CustomerRoomSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.CustomerRoomDTO;
import com.social.eshop.service.mapper.CustomerRoomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
>>>>>>> with_entities
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
<<<<<<< HEAD

    private final CustomerRoomRepository customerRoomRepository;

    private final CustomerRoomSearchRepository customerRoomSearchRepository;

    public CustomerRoomServiceImpl(CustomerRoomRepository customerRoomRepository, CustomerRoomSearchRepository customerRoomSearchRepository) {
        this.customerRoomRepository = customerRoomRepository;
=======
    
    private final CustomerRoomRepository customerRoomRepository;

    private final CustomerRoomMapper customerRoomMapper;

    private final CustomerRoomSearchRepository customerRoomSearchRepository;

    public CustomerRoomServiceImpl(CustomerRoomRepository customerRoomRepository, CustomerRoomMapper customerRoomMapper, CustomerRoomSearchRepository customerRoomSearchRepository) {
        this.customerRoomRepository = customerRoomRepository;
        this.customerRoomMapper = customerRoomMapper;
>>>>>>> with_entities
        this.customerRoomSearchRepository = customerRoomSearchRepository;
    }

    /**
     * Save a customerRoom.
     *
<<<<<<< HEAD
     * @param customerRoom the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerRoom save(CustomerRoom customerRoom) {
        log.debug("Request to save CustomerRoom : {}", customerRoom);
        CustomerRoom result = customerRoomRepository.save(customerRoom);
        customerRoomSearchRepository.save(result);
=======
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
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the customerRooms.
<<<<<<< HEAD
     *
=======
     *  
>>>>>>> with_entities
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<CustomerRoom> findAll() {
        log.debug("Request to get all CustomerRooms");
        return customerRoomRepository.findAll();
=======
    public List<CustomerRoomDTO> findAll() {
        log.debug("Request to get all CustomerRooms");
        List<CustomerRoomDTO> result = customerRoomRepository.findAll().stream()
            .map(customerRoomMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one customerRoom by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public CustomerRoom findOne(Long id) {
        log.debug("Request to get CustomerRoom : {}", id);
        return customerRoomRepository.findOne(id);
=======
    public CustomerRoomDTO findOne(Long id) {
        log.debug("Request to get CustomerRoom : {}", id);
        CustomerRoom customerRoom = customerRoomRepository.findOne(id);
        CustomerRoomDTO customerRoomDTO = customerRoomMapper.toDto(customerRoom);
        return customerRoomDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD
    public List<CustomerRoom> search(String query) {
        log.debug("Request to search CustomerRooms for query {}", query);
        return StreamSupport
            .stream(customerRoomSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<CustomerRoomDTO> search(String query) {
        log.debug("Request to search CustomerRooms for query {}", query);
        return StreamSupport
            .stream(customerRoomSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerRoomMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

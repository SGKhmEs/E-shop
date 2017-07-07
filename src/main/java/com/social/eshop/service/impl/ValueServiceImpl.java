package com.social.eshop.service.impl;

import com.social.eshop.service.ValueService;
import com.social.eshop.domain.Value;
import com.social.eshop.repository.ValueRepository;
import com.social.eshop.repository.search.ValueSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.ValueDTO;
import com.social.eshop.service.mapper.ValueMapper;
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
 * Service Implementation for managing Value.
 */
@Service
@Transactional
public class ValueServiceImpl implements ValueService{

    private final Logger log = LoggerFactory.getLogger(ValueServiceImpl.class);
<<<<<<< HEAD

    private final ValueRepository valueRepository;

    private final ValueSearchRepository valueSearchRepository;

    public ValueServiceImpl(ValueRepository valueRepository, ValueSearchRepository valueSearchRepository) {
        this.valueRepository = valueRepository;
=======
    
    private final ValueRepository valueRepository;

    private final ValueMapper valueMapper;

    private final ValueSearchRepository valueSearchRepository;

    public ValueServiceImpl(ValueRepository valueRepository, ValueMapper valueMapper, ValueSearchRepository valueSearchRepository) {
        this.valueRepository = valueRepository;
        this.valueMapper = valueMapper;
>>>>>>> with_entities
        this.valueSearchRepository = valueSearchRepository;
    }

    /**
     * Save a value.
     *
<<<<<<< HEAD
     * @param value the entity to save
     * @return the persisted entity
     */
    @Override
    public Value save(Value value) {
        log.debug("Request to save Value : {}", value);
        Value result = valueRepository.save(value);
        valueSearchRepository.save(result);
=======
     * @param valueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ValueDTO save(ValueDTO valueDTO) {
        log.debug("Request to save Value : {}", valueDTO);
        Value value = valueMapper.toEntity(valueDTO);
        value = valueRepository.save(value);
        ValueDTO result = valueMapper.toDto(value);
        valueSearchRepository.save(value);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the values.
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
    public List<Value> findAll() {
        log.debug("Request to get all Values");
        return valueRepository.findAll();
=======
    public List<ValueDTO> findAll() {
        log.debug("Request to get all Values");
        List<ValueDTO> result = valueRepository.findAll().stream()
            .map(valueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one value by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Value findOne(Long id) {
        log.debug("Request to get Value : {}", id);
        return valueRepository.findOne(id);
=======
    public ValueDTO findOne(Long id) {
        log.debug("Request to get Value : {}", id);
        Value value = valueRepository.findOne(id);
        ValueDTO valueDTO = valueMapper.toDto(value);
        return valueDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  value by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Value : {}", id);
        valueRepository.delete(id);
        valueSearchRepository.delete(id);
    }

    /**
     * Search for the value corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Value> search(String query) {
        log.debug("Request to search Values for query {}", query);
        return StreamSupport
            .stream(valueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<ValueDTO> search(String query) {
        log.debug("Request to search Values for query {}", query);
        return StreamSupport
            .stream(valueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(valueMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

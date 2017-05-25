package com.social.eshop.service.impl;

import com.social.eshop.service.ValueService;
import com.social.eshop.domain.Value;
import com.social.eshop.repository.ValueRepository;
import com.social.eshop.repository.search.ValueSearchRepository;
import com.social.eshop.service.dto.ValueDTO;
import com.social.eshop.service.mapper.ValueMapper;
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
 * Service Implementation for managing Value.
 */
@Service
@Transactional
public class ValueServiceImpl implements ValueService{

    private final Logger log = LoggerFactory.getLogger(ValueServiceImpl.class);
    
    private final ValueRepository valueRepository;

    private final ValueMapper valueMapper;

    private final ValueSearchRepository valueSearchRepository;

    public ValueServiceImpl(ValueRepository valueRepository, ValueMapper valueMapper, ValueSearchRepository valueSearchRepository) {
        this.valueRepository = valueRepository;
        this.valueMapper = valueMapper;
        this.valueSearchRepository = valueSearchRepository;
    }

    /**
     * Save a value.
     *
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
        return result;
    }

    /**
     *  Get all the values.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ValueDTO> findAll() {
        log.debug("Request to get all Values");
        List<ValueDTO> result = valueRepository.findAll().stream()
            .map(valueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one value by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ValueDTO findOne(Long id) {
        log.debug("Request to get Value : {}", id);
        Value value = valueRepository.findOne(id);
        ValueDTO valueDTO = valueMapper.toDto(value);
        return valueDTO;
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
    public List<ValueDTO> search(String query) {
        log.debug("Request to search Values for query {}", query);
        return StreamSupport
            .stream(valueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(valueMapper::toDto)
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.ValueService;
import com.social.eshop.domain.Value;
import com.social.eshop.repository.ValueRepository;
import com.social.eshop.repository.search.ValueSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ValueSearchRepository valueSearchRepository;

    public ValueServiceImpl(ValueRepository valueRepository, ValueSearchRepository valueSearchRepository) {
        this.valueRepository = valueRepository;
        this.valueSearchRepository = valueSearchRepository;
    }

    /**
     * Save a value.
     *
     * @param value the entity to save
     * @return the persisted entity
     */
    @Override
    public Value save(Value value) {
        log.debug("Request to save Value : {}", value);
        Value result = valueRepository.save(value);
        valueSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the values.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Value> findAll() {
        log.debug("Request to get all Values");
        return valueRepository.findAll();
    }

    /**
     *  Get one value by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Value findOne(Long id) {
        log.debug("Request to get Value : {}", id);
        return valueRepository.findOne(id);
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
    public List<Value> search(String query) {
        log.debug("Request to search Values for query {}", query);
        return StreamSupport
            .stream(valueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

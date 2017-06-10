package com.social.eshop.service.impl;

import com.social.eshop.service.ProducersService;
import com.social.eshop.domain.Producers;
import com.social.eshop.repository.ProducersRepository;
import com.social.eshop.repository.search.ProducersSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Producers.
 */
@Service
@Transactional
public class ProducersServiceImpl implements ProducersService{

    private final Logger log = LoggerFactory.getLogger(ProducersServiceImpl.class);

    private final ProducersRepository producersRepository;

    private final ProducersSearchRepository producersSearchRepository;

    public ProducersServiceImpl(ProducersRepository producersRepository, ProducersSearchRepository producersSearchRepository) {
        this.producersRepository = producersRepository;
        this.producersSearchRepository = producersSearchRepository;
    }

    /**
     * Save a producers.
     *
     * @param producers the entity to save
     * @return the persisted entity
     */
    @Override
    public Producers save(Producers producers) {
        log.debug("Request to save Producers : {}", producers);
        Producers result = producersRepository.save(producers);
        producersSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the producers.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producers> findAll() {
        log.debug("Request to get all Producers");
        return producersRepository.findAll();
    }

    /**
     *  Get one producers by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Producers findOne(Long id) {
        log.debug("Request to get Producers : {}", id);
        return producersRepository.findOne(id);
    }

    /**
     *  Delete the  producers by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Producers : {}", id);
        producersRepository.delete(id);
        producersSearchRepository.delete(id);
    }

    /**
     * Search for the producers corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producers> search(String query) {
        log.debug("Request to search Producers for query {}", query);
        return StreamSupport
            .stream(producersSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

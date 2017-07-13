package com.social.eshop.service.impl;

import com.social.eshop.service.ProducersService;
import com.social.eshop.domain.Producers;
import com.social.eshop.repository.ProducersRepository;
import com.social.eshop.repository.search.ProducersSearchRepository;
import com.social.eshop.service.dto.ProducersDTO;
import com.social.eshop.service.mapper.ProducersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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

    private final ProducersMapper producersMapper;

    private final ProducersSearchRepository producersSearchRepository;

    public ProducersServiceImpl(ProducersRepository producersRepository, ProducersMapper producersMapper, ProducersSearchRepository producersSearchRepository) {
        this.producersRepository = producersRepository;
        this.producersMapper = producersMapper;
        this.producersSearchRepository = producersSearchRepository;
    }

    /**
     * Save a producers.
     *
     * @param producersDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProducersDTO save(ProducersDTO producersDTO) {
        log.debug("Request to save Producers : {}", producersDTO);
        Producers producers = producersMapper.toEntity(producersDTO);
        producers = producersRepository.save(producers);
        ProducersDTO result = producersMapper.toDto(producers);
        producersSearchRepository.save(producers);
        return result;
    }

    /**
     *  Get all the producers.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProducersDTO> findAll() {
        log.debug("Request to get all Producers");
        return producersRepository.findAll().stream()
            .map(producersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one producers by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProducersDTO findOne(Long id) {
        log.debug("Request to get Producers : {}", id);
        Producers producers = producersRepository.findOne(id);
        return producersMapper.toDto(producers);
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
    public List<ProducersDTO> search(String query) {
        log.debug("Request to search Producers for query {}", query);
        return StreamSupport
            .stream(producersSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(producersMapper::toDto)
            .collect(Collectors.toList());
    }
}

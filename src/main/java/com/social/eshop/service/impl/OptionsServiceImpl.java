package com.social.eshop.service.impl;

import com.social.eshop.service.OptionsService;
import com.social.eshop.domain.Options;
import com.social.eshop.repository.OptionsRepository;
import com.social.eshop.repository.search.OptionsSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.OptionsDTO;
import com.social.eshop.service.mapper.OptionsMapper;
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
 * Service Implementation for managing Options.
 */
@Service
@Transactional
public class OptionsServiceImpl implements OptionsService{

    private final Logger log = LoggerFactory.getLogger(OptionsServiceImpl.class);
<<<<<<< HEAD

    private final OptionsRepository optionsRepository;

    private final OptionsSearchRepository optionsSearchRepository;

    public OptionsServiceImpl(OptionsRepository optionsRepository, OptionsSearchRepository optionsSearchRepository) {
        this.optionsRepository = optionsRepository;
=======
    
    private final OptionsRepository optionsRepository;

    private final OptionsMapper optionsMapper;

    private final OptionsSearchRepository optionsSearchRepository;

    public OptionsServiceImpl(OptionsRepository optionsRepository, OptionsMapper optionsMapper, OptionsSearchRepository optionsSearchRepository) {
        this.optionsRepository = optionsRepository;
        this.optionsMapper = optionsMapper;
>>>>>>> with_entities
        this.optionsSearchRepository = optionsSearchRepository;
    }

    /**
     * Save a options.
     *
<<<<<<< HEAD
     * @param options the entity to save
     * @return the persisted entity
     */
    @Override
    public Options save(Options options) {
        log.debug("Request to save Options : {}", options);
        Options result = optionsRepository.save(options);
        optionsSearchRepository.save(result);
=======
     * @param optionsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OptionsDTO save(OptionsDTO optionsDTO) {
        log.debug("Request to save Options : {}", optionsDTO);
        Options options = optionsMapper.toEntity(optionsDTO);
        options = optionsRepository.save(options);
        OptionsDTO result = optionsMapper.toDto(options);
        optionsSearchRepository.save(options);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the options.
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
    public List<Options> findAll() {
        log.debug("Request to get all Options");
        return optionsRepository.findAll();
=======
    public List<OptionsDTO> findAll() {
        log.debug("Request to get all Options");
        List<OptionsDTO> result = optionsRepository.findAll().stream()
            .map(optionsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one options by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Options findOne(Long id) {
        log.debug("Request to get Options : {}", id);
        return optionsRepository.findOne(id);
=======
    public OptionsDTO findOne(Long id) {
        log.debug("Request to get Options : {}", id);
        Options options = optionsRepository.findOne(id);
        OptionsDTO optionsDTO = optionsMapper.toDto(options);
        return optionsDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  options by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Options : {}", id);
        optionsRepository.delete(id);
        optionsSearchRepository.delete(id);
    }

    /**
     * Search for the options corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Options> search(String query) {
        log.debug("Request to search Options for query {}", query);
        return StreamSupport
            .stream(optionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<OptionsDTO> search(String query) {
        log.debug("Request to search Options for query {}", query);
        return StreamSupport
            .stream(optionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(optionsMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

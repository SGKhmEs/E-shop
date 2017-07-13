package com.social.eshop.service.impl;

import com.social.eshop.service.OptionsService;
import com.social.eshop.domain.Options;
import com.social.eshop.repository.OptionsRepository;
import com.social.eshop.repository.search.OptionsSearchRepository;
import com.social.eshop.service.dto.OptionsDTO;
import com.social.eshop.service.mapper.OptionsMapper;
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
 * Service Implementation for managing Options.
 */
@Service
@Transactional
public class OptionsServiceImpl implements OptionsService{

    private final Logger log = LoggerFactory.getLogger(OptionsServiceImpl.class);

    private final OptionsRepository optionsRepository;

    private final OptionsMapper optionsMapper;

    private final OptionsSearchRepository optionsSearchRepository;

    public OptionsServiceImpl(OptionsRepository optionsRepository, OptionsMapper optionsMapper, OptionsSearchRepository optionsSearchRepository) {
        this.optionsRepository = optionsRepository;
        this.optionsMapper = optionsMapper;
        this.optionsSearchRepository = optionsSearchRepository;
    }

    /**
     * Save a options.
     *
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
        return result;
    }

    /**
     *  Get all the options.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OptionsDTO> findAll() {
        log.debug("Request to get all Options");
        return optionsRepository.findAll().stream()
            .map(optionsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one options by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OptionsDTO findOne(Long id) {
        log.debug("Request to get Options : {}", id);
        Options options = optionsRepository.findOne(id);
        return optionsMapper.toDto(options);
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
    public List<OptionsDTO> search(String query) {
        log.debug("Request to search Options for query {}", query);
        return StreamSupport
            .stream(optionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(optionsMapper::toDto)
            .collect(Collectors.toList());
    }
}

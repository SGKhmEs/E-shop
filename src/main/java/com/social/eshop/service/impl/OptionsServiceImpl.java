package com.social.eshop.service.impl;

import com.social.eshop.service.OptionsService;
import com.social.eshop.domain.Options;
import com.social.eshop.repository.OptionsRepository;
import com.social.eshop.repository.search.OptionsSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final OptionsSearchRepository optionsSearchRepository;

    public OptionsServiceImpl(OptionsRepository optionsRepository, OptionsSearchRepository optionsSearchRepository) {
        this.optionsRepository = optionsRepository;
        this.optionsSearchRepository = optionsSearchRepository;
    }

    /**
     * Save a options.
     *
     * @param options the entity to save
     * @return the persisted entity
     */
    @Override
    public Options save(Options options) {
        log.debug("Request to save Options : {}", options);
        Options result = optionsRepository.save(options);
        optionsSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the options.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Options> findAll() {
        log.debug("Request to get all Options");
        return optionsRepository.findAll();
    }

    /**
     *  Get one options by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Options findOne(Long id) {
        log.debug("Request to get Options : {}", id);
        return optionsRepository.findOne(id);
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
    public List<Options> search(String query) {
        log.debug("Request to search Options for query {}", query);
        return StreamSupport
            .stream(optionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

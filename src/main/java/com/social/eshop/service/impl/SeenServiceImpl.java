package com.social.eshop.service.impl;

import com.social.eshop.service.SeenService;
import com.social.eshop.domain.Seen;
import com.social.eshop.repository.SeenRepository;
import com.social.eshop.repository.search.SeenSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Seen.
 */
@Service
@Transactional
public class SeenServiceImpl implements SeenService{

    private final Logger log = LoggerFactory.getLogger(SeenServiceImpl.class);

    private final SeenRepository seenRepository;

    private final SeenSearchRepository seenSearchRepository;

    public SeenServiceImpl(SeenRepository seenRepository, SeenSearchRepository seenSearchRepository) {
        this.seenRepository = seenRepository;
        this.seenSearchRepository = seenSearchRepository;
    }

    /**
     * Save a seen.
     *
     * @param seen the entity to save
     * @return the persisted entity
     */
    @Override
    public Seen save(Seen seen) {
        log.debug("Request to save Seen : {}", seen);
        Seen result = seenRepository.save(seen);
        seenSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the seens.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Seen> findAll() {
        log.debug("Request to get all Seens");
        return seenRepository.findAll();
    }

    /**
     *  Get one seen by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Seen findOne(Long id) {
        log.debug("Request to get Seen : {}", id);
        return seenRepository.findOne(id);
    }

    /**
     *  Delete the  seen by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Seen : {}", id);
        seenRepository.delete(id);
        seenSearchRepository.delete(id);
    }

    /**
     * Search for the seen corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Seen> search(String query) {
        log.debug("Request to search Seens for query {}", query);
        return StreamSupport
            .stream(seenSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

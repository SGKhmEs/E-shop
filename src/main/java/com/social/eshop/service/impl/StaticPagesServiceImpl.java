package com.social.eshop.service.impl;

import com.social.eshop.service.StaticPagesService;
import com.social.eshop.domain.StaticPages;
import com.social.eshop.repository.StaticPagesRepository;
import com.social.eshop.repository.search.StaticPagesSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StaticPages.
 */
@Service
@Transactional
public class StaticPagesServiceImpl implements StaticPagesService{

    private final Logger log = LoggerFactory.getLogger(StaticPagesServiceImpl.class);

    private final StaticPagesRepository staticPagesRepository;

    private final StaticPagesSearchRepository staticPagesSearchRepository;

    public StaticPagesServiceImpl(StaticPagesRepository staticPagesRepository, StaticPagesSearchRepository staticPagesSearchRepository) {
        this.staticPagesRepository = staticPagesRepository;
        this.staticPagesSearchRepository = staticPagesSearchRepository;
    }

    /**
     * Save a staticPages.
     *
     * @param staticPages the entity to save
     * @return the persisted entity
     */
    @Override
    public StaticPages save(StaticPages staticPages) {
        log.debug("Request to save StaticPages : {}", staticPages);
        StaticPages result = staticPagesRepository.save(staticPages);
        staticPagesSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the staticPages.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StaticPages> findAll() {
        log.debug("Request to get all StaticPages");
        return staticPagesRepository.findAll();
    }

    /**
     *  Get one staticPages by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StaticPages findOne(Long id) {
        log.debug("Request to get StaticPages : {}", id);
        return staticPagesRepository.findOne(id);
    }

    /**
     *  Delete the  staticPages by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StaticPages : {}", id);
        staticPagesRepository.delete(id);
        staticPagesSearchRepository.delete(id);
    }

    /**
     * Search for the staticPages corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StaticPages> search(String query) {
        log.debug("Request to search StaticPages for query {}", query);
        return StreamSupport
            .stream(staticPagesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

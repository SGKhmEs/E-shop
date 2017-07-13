package com.social.eshop.service.impl;

import com.social.eshop.service.StaticPagesService;
import com.social.eshop.domain.StaticPages;
import com.social.eshop.repository.StaticPagesRepository;
import com.social.eshop.repository.search.StaticPagesSearchRepository;
import com.social.eshop.service.dto.StaticPagesDTO;
import com.social.eshop.service.mapper.StaticPagesMapper;
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
 * Service Implementation for managing StaticPages.
 */
@Service
@Transactional
public class StaticPagesServiceImpl implements StaticPagesService{

    private final Logger log = LoggerFactory.getLogger(StaticPagesServiceImpl.class);

    private final StaticPagesRepository staticPagesRepository;

    private final StaticPagesMapper staticPagesMapper;

    private final StaticPagesSearchRepository staticPagesSearchRepository;

    public StaticPagesServiceImpl(StaticPagesRepository staticPagesRepository, StaticPagesMapper staticPagesMapper, StaticPagesSearchRepository staticPagesSearchRepository) {
        this.staticPagesRepository = staticPagesRepository;
        this.staticPagesMapper = staticPagesMapper;
        this.staticPagesSearchRepository = staticPagesSearchRepository;
    }

    /**
     * Save a staticPages.
     *
     * @param staticPagesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StaticPagesDTO save(StaticPagesDTO staticPagesDTO) {
        log.debug("Request to save StaticPages : {}", staticPagesDTO);
        StaticPages staticPages = staticPagesMapper.toEntity(staticPagesDTO);
        staticPages = staticPagesRepository.save(staticPages);
        StaticPagesDTO result = staticPagesMapper.toDto(staticPages);
        staticPagesSearchRepository.save(staticPages);
        return result;
    }

    /**
     *  Get all the staticPages.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StaticPagesDTO> findAll() {
        log.debug("Request to get all StaticPages");
        return staticPagesRepository.findAll().stream()
            .map(staticPagesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one staticPages by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StaticPagesDTO findOne(Long id) {
        log.debug("Request to get StaticPages : {}", id);
        StaticPages staticPages = staticPagesRepository.findOne(id);
        return staticPagesMapper.toDto(staticPages);
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
    public List<StaticPagesDTO> search(String query) {
        log.debug("Request to search StaticPages for query {}", query);
        return StreamSupport
            .stream(staticPagesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(staticPagesMapper::toDto)
            .collect(Collectors.toList());
    }
}

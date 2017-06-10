package com.social.eshop.service.impl;

import com.social.eshop.service.TagsService;
import com.social.eshop.domain.Tags;
import com.social.eshop.repository.TagsRepository;
import com.social.eshop.repository.search.TagsSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Tags.
 */
@Service
@Transactional
public class TagsServiceImpl implements TagsService{

    private final Logger log = LoggerFactory.getLogger(TagsServiceImpl.class);

    private final TagsRepository tagsRepository;

    private final TagsSearchRepository tagsSearchRepository;

    public TagsServiceImpl(TagsRepository tagsRepository, TagsSearchRepository tagsSearchRepository) {
        this.tagsRepository = tagsRepository;
        this.tagsSearchRepository = tagsSearchRepository;
    }

    /**
     * Save a tags.
     *
     * @param tags the entity to save
     * @return the persisted entity
     */
    @Override
    public Tags save(Tags tags) {
        log.debug("Request to save Tags : {}", tags);
        Tags result = tagsRepository.save(tags);
        tagsSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the tags.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Tags> findAll() {
        log.debug("Request to get all Tags");
        return tagsRepository.findAll();
    }

    /**
     *  Get one tags by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Tags findOne(Long id) {
        log.debug("Request to get Tags : {}", id);
        return tagsRepository.findOne(id);
    }

    /**
     *  Delete the  tags by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tags : {}", id);
        tagsRepository.delete(id);
        tagsSearchRepository.delete(id);
    }

    /**
     * Search for the tags corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Tags> search(String query) {
        log.debug("Request to search Tags for query {}", query);
        return StreamSupport
            .stream(tagsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

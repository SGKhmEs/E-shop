package com.social.eshop.service.impl;

import com.social.eshop.service.TagsService;
import com.social.eshop.domain.Tags;
import com.social.eshop.repository.TagsRepository;
import com.social.eshop.repository.search.TagsSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.TagsDTO;
import com.social.eshop.service.mapper.TagsMapper;
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
 * Service Implementation for managing Tags.
 */
@Service
@Transactional
public class TagsServiceImpl implements TagsService{

    private final Logger log = LoggerFactory.getLogger(TagsServiceImpl.class);
<<<<<<< HEAD

    private final TagsRepository tagsRepository;

    private final TagsSearchRepository tagsSearchRepository;

    public TagsServiceImpl(TagsRepository tagsRepository, TagsSearchRepository tagsSearchRepository) {
        this.tagsRepository = tagsRepository;
=======
    
    private final TagsRepository tagsRepository;

    private final TagsMapper tagsMapper;

    private final TagsSearchRepository tagsSearchRepository;

    public TagsServiceImpl(TagsRepository tagsRepository, TagsMapper tagsMapper, TagsSearchRepository tagsSearchRepository) {
        this.tagsRepository = tagsRepository;
        this.tagsMapper = tagsMapper;
>>>>>>> with_entities
        this.tagsSearchRepository = tagsSearchRepository;
    }

    /**
     * Save a tags.
     *
<<<<<<< HEAD
     * @param tags the entity to save
     * @return the persisted entity
     */
    @Override
    public Tags save(Tags tags) {
        log.debug("Request to save Tags : {}", tags);
        Tags result = tagsRepository.save(tags);
        tagsSearchRepository.save(result);
=======
     * @param tagsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TagsDTO save(TagsDTO tagsDTO) {
        log.debug("Request to save Tags : {}", tagsDTO);
        Tags tags = tagsMapper.toEntity(tagsDTO);
        tags = tagsRepository.save(tags);
        TagsDTO result = tagsMapper.toDto(tags);
        tagsSearchRepository.save(tags);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the tags.
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
    public List<Tags> findAll() {
        log.debug("Request to get all Tags");
        return tagsRepository.findAll();
=======
    public List<TagsDTO> findAll() {
        log.debug("Request to get all Tags");
        List<TagsDTO> result = tagsRepository.findAll().stream()
            .map(tagsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one tags by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Tags findOne(Long id) {
        log.debug("Request to get Tags : {}", id);
        return tagsRepository.findOne(id);
=======
    public TagsDTO findOne(Long id) {
        log.debug("Request to get Tags : {}", id);
        Tags tags = tagsRepository.findOne(id);
        TagsDTO tagsDTO = tagsMapper.toDto(tags);
        return tagsDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD
    public List<Tags> search(String query) {
        log.debug("Request to search Tags for query {}", query);
        return StreamSupport
            .stream(tagsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<TagsDTO> search(String query) {
        log.debug("Request to search Tags for query {}", query);
        return StreamSupport
            .stream(tagsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(tagsMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

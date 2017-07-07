package com.social.eshop.service.impl;

import com.social.eshop.service.CommentsService;
import com.social.eshop.domain.Comments;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.repository.search.CommentsSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.CommentsDTO;
import com.social.eshop.service.mapper.CommentsMapper;
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
 * Service Implementation for managing Comments.
 */
@Service
@Transactional
public class CommentsServiceImpl implements CommentsService{

    private final Logger log = LoggerFactory.getLogger(CommentsServiceImpl.class);
<<<<<<< HEAD

    private final CommentsRepository commentsRepository;

    private final CommentsSearchRepository commentsSearchRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository, CommentsSearchRepository commentsSearchRepository) {
        this.commentsRepository = commentsRepository;
=======
    
    private final CommentsRepository commentsRepository;

    private final CommentsMapper commentsMapper;

    private final CommentsSearchRepository commentsSearchRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository, CommentsMapper commentsMapper, CommentsSearchRepository commentsSearchRepository) {
        this.commentsRepository = commentsRepository;
        this.commentsMapper = commentsMapper;
>>>>>>> with_entities
        this.commentsSearchRepository = commentsSearchRepository;
    }

    /**
     * Save a comments.
     *
<<<<<<< HEAD
     * @param comments the entity to save
     * @return the persisted entity
     */
    @Override
    public Comments save(Comments comments) {
        log.debug("Request to save Comments : {}", comments);
        Comments result = commentsRepository.save(comments);
        commentsSearchRepository.save(result);
=======
     * @param commentsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommentsDTO save(CommentsDTO commentsDTO) {
        log.debug("Request to save Comments : {}", commentsDTO);
        Comments comments = commentsMapper.toEntity(commentsDTO);
        comments = commentsRepository.save(comments);
        CommentsDTO result = commentsMapper.toDto(comments);
        commentsSearchRepository.save(comments);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the comments.
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
    public List<Comments> findAll() {
        log.debug("Request to get all Comments");
        return commentsRepository.findAll();
=======
    public List<CommentsDTO> findAll() {
        log.debug("Request to get all Comments");
        List<CommentsDTO> result = commentsRepository.findAll().stream()
            .map(commentsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one comments by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Comments findOne(Long id) {
        log.debug("Request to get Comments : {}", id);
        return commentsRepository.findOne(id);
=======
    public CommentsDTO findOne(Long id) {
        log.debug("Request to get Comments : {}", id);
        Comments comments = commentsRepository.findOne(id);
        CommentsDTO commentsDTO = commentsMapper.toDto(comments);
        return commentsDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  comments by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comments : {}", id);
        commentsRepository.delete(id);
        commentsSearchRepository.delete(id);
    }

    /**
     * Search for the comments corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Comments> search(String query) {
        log.debug("Request to search Comments for query {}", query);
        return StreamSupport
            .stream(commentsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<CommentsDTO> search(String query) {
        log.debug("Request to search Comments for query {}", query);
        return StreamSupport
            .stream(commentsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(commentsMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.CommentsService;
import com.social.eshop.domain.Comments;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.repository.search.CommentsSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final CommentsRepository commentsRepository;

    private final CommentsSearchRepository commentsSearchRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository, CommentsSearchRepository commentsSearchRepository) {
        this.commentsRepository = commentsRepository;
        this.commentsSearchRepository = commentsSearchRepository;
    }

    /**
     * Save a comments.
     *
     * @param comments the entity to save
     * @return the persisted entity
     */
    @Override
    public Comments save(Comments comments) {
        log.debug("Request to save Comments : {}", comments);
        Comments result = commentsRepository.save(comments);
        commentsSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the comments.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Comments> findAll() {
        log.debug("Request to get all Comments");
        return commentsRepository.findAll();
    }

    /**
     *  Get one comments by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Comments findOne(Long id) {
        log.debug("Request to get Comments : {}", id);
        return commentsRepository.findOne(id);
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
    public List<Comments> search(String query) {
        log.debug("Request to search Comments for query {}", query);
        return StreamSupport
            .stream(commentsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

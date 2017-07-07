package com.social.eshop.service.impl;

import com.social.eshop.service.SessionIdService;
import com.social.eshop.domain.SessionId;
import com.social.eshop.repository.SessionIdRepository;
import com.social.eshop.repository.search.SessionIdSearchRepository;
import com.social.eshop.service.dto.SessionIdDTO;
import com.social.eshop.service.mapper.SessionIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SessionId.
 */
@Service
@Transactional
public class SessionIdServiceImpl implements SessionIdService{

    private final Logger log = LoggerFactory.getLogger(SessionIdServiceImpl.class);
    
    private final SessionIdRepository sessionIdRepository;

    private final SessionIdMapper sessionIdMapper;

    private final SessionIdSearchRepository sessionIdSearchRepository;

    public SessionIdServiceImpl(SessionIdRepository sessionIdRepository, SessionIdMapper sessionIdMapper, SessionIdSearchRepository sessionIdSearchRepository) {
        this.sessionIdRepository = sessionIdRepository;
        this.sessionIdMapper = sessionIdMapper;
        this.sessionIdSearchRepository = sessionIdSearchRepository;
    }

    /**
     * Save a sessionId.
     *
     * @param sessionIdDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SessionIdDTO save(SessionIdDTO sessionIdDTO) {
        log.debug("Request to save SessionId : {}", sessionIdDTO);
        SessionId sessionId = sessionIdMapper.toEntity(sessionIdDTO);
        sessionId = sessionIdRepository.save(sessionId);
        SessionIdDTO result = sessionIdMapper.toDto(sessionId);
        sessionIdSearchRepository.save(sessionId);
        return result;
    }

    /**
     *  Get all the sessionIds.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SessionIdDTO> findAll() {
        log.debug("Request to get all SessionIds");
        List<SessionIdDTO> result = sessionIdRepository.findAll().stream()
            .map(sessionIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one sessionId by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SessionIdDTO findOne(Long id) {
        log.debug("Request to get SessionId : {}", id);
        SessionId sessionId = sessionIdRepository.findOne(id);
        SessionIdDTO sessionIdDTO = sessionIdMapper.toDto(sessionId);
        return sessionIdDTO;
    }

    /**
     *  Delete the  sessionId by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SessionId : {}", id);
        sessionIdRepository.delete(id);
        sessionIdSearchRepository.delete(id);
    }

    /**
     * Search for the sessionId corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SessionIdDTO> search(String query) {
        log.debug("Request to search SessionIds for query {}", query);
        return StreamSupport
            .stream(sessionIdSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(sessionIdMapper::toDto)
            .collect(Collectors.toList());
    }
}

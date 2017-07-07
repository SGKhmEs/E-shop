package com.social.eshop.service;

import com.social.eshop.service.dto.SessionIdDTO;
import java.util.List;

/**
 * Service Interface for managing SessionId.
 */
public interface SessionIdService {

    /**
     * Save a sessionId.
     *
     * @param sessionIdDTO the entity to save
     * @return the persisted entity
     */
    SessionIdDTO save(SessionIdDTO sessionIdDTO);

    /**
     *  Get all the sessionIds.
     *  
     *  @return the list of entities
     */
    List<SessionIdDTO> findAll();

    /**
     *  Get the "id" sessionId.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SessionIdDTO findOne(Long id);

    /**
     *  Delete the "id" sessionId.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the sessionId corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<SessionIdDTO> search(String query);
}

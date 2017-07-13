package com.social.eshop.service;

import com.social.eshop.service.dto.MediaDTO;
import java.util.List;

/**
 * Service Interface for managing Media.
 */
public interface MediaService {

    /**
     * Save a media.
     *
     * @param mediaDTO the entity to save
     * @return the persisted entity
     */
    MediaDTO save(MediaDTO mediaDTO);

    /**
     *  Get all the media.
     *
     *  @return the list of entities
     */
    List<MediaDTO> findAll();

    /**
     *  Get the "id" media.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MediaDTO findOne(Long id);

    /**
     *  Delete the "id" media.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the media corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<MediaDTO> search(String query);
}

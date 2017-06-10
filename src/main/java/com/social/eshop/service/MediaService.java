package com.social.eshop.service;

import com.social.eshop.domain.Media;
import java.util.List;

/**
 * Service Interface for managing Media.
 */
public interface MediaService {

    /**
     * Save a media.
     *
     * @param media the entity to save
     * @return the persisted entity
     */
    Media save(Media media);

    /**
     *  Get all the media.
     *
     *  @return the list of entities
     */
    List<Media> findAll();

    /**
     *  Get the "id" media.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Media findOne(Long id);

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
    List<Media> search(String query);
}

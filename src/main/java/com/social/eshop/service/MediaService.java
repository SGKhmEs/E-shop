package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Media;
=======
import com.social.eshop.service.dto.MediaDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Media.
 */
public interface MediaService {

    /**
     * Save a media.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" media.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Media findOne(Long id);
=======
    MediaDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Media> search(String query);
=======
    List<MediaDTO> search(String query);
>>>>>>> with_entities
}

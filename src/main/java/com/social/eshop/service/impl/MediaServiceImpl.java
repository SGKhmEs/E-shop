package com.social.eshop.service.impl;

import com.social.eshop.service.MediaService;
import com.social.eshop.domain.Media;
import com.social.eshop.repository.MediaRepository;
import com.social.eshop.repository.search.MediaSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Media.
 */
@Service
@Transactional
public class MediaServiceImpl implements MediaService{

    private final Logger log = LoggerFactory.getLogger(MediaServiceImpl.class);

    private final MediaRepository mediaRepository;

    private final MediaSearchRepository mediaSearchRepository;

    public MediaServiceImpl(MediaRepository mediaRepository, MediaSearchRepository mediaSearchRepository) {
        this.mediaRepository = mediaRepository;
        this.mediaSearchRepository = mediaSearchRepository;
    }

    /**
     * Save a media.
     *
     * @param media the entity to save
     * @return the persisted entity
     */
    @Override
    public Media save(Media media) {
        log.debug("Request to save Media : {}", media);
        Media result = mediaRepository.save(media);
        mediaSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the media.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Media> findAll() {
        log.debug("Request to get all Media");
        return mediaRepository.findAll();
    }

    /**
     *  Get one media by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Media findOne(Long id) {
        log.debug("Request to get Media : {}", id);
        return mediaRepository.findOne(id);
    }

    /**
     *  Delete the  media by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Media : {}", id);
        mediaRepository.delete(id);
        mediaSearchRepository.delete(id);
    }

    /**
     * Search for the media corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Media> search(String query) {
        log.debug("Request to search Media for query {}", query);
        return StreamSupport
            .stream(mediaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

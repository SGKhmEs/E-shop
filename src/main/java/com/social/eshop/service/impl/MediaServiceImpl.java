package com.social.eshop.service.impl;

import com.social.eshop.service.MediaService;
import com.social.eshop.domain.Media;
import com.social.eshop.repository.MediaRepository;
import com.social.eshop.repository.search.MediaSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.MediaDTO;
import com.social.eshop.service.mapper.MediaMapper;
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
 * Service Implementation for managing Media.
 */
@Service
@Transactional
public class MediaServiceImpl implements MediaService{

    private final Logger log = LoggerFactory.getLogger(MediaServiceImpl.class);
<<<<<<< HEAD

    private final MediaRepository mediaRepository;

    private final MediaSearchRepository mediaSearchRepository;

    public MediaServiceImpl(MediaRepository mediaRepository, MediaSearchRepository mediaSearchRepository) {
        this.mediaRepository = mediaRepository;
=======
    
    private final MediaRepository mediaRepository;

    private final MediaMapper mediaMapper;

    private final MediaSearchRepository mediaSearchRepository;

    public MediaServiceImpl(MediaRepository mediaRepository, MediaMapper mediaMapper, MediaSearchRepository mediaSearchRepository) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
>>>>>>> with_entities
        this.mediaSearchRepository = mediaSearchRepository;
    }

    /**
     * Save a media.
     *
<<<<<<< HEAD
     * @param media the entity to save
     * @return the persisted entity
     */
    @Override
    public Media save(Media media) {
        log.debug("Request to save Media : {}", media);
        Media result = mediaRepository.save(media);
        mediaSearchRepository.save(result);
=======
     * @param mediaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MediaDTO save(MediaDTO mediaDTO) {
        log.debug("Request to save Media : {}", mediaDTO);
        Media media = mediaMapper.toEntity(mediaDTO);
        media = mediaRepository.save(media);
        MediaDTO result = mediaMapper.toDto(media);
        mediaSearchRepository.save(media);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the media.
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
    public List<Media> findAll() {
        log.debug("Request to get all Media");
        return mediaRepository.findAll();
=======
    public List<MediaDTO> findAll() {
        log.debug("Request to get all Media");
        List<MediaDTO> result = mediaRepository.findAll().stream()
            .map(mediaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one media by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Media findOne(Long id) {
        log.debug("Request to get Media : {}", id);
        return mediaRepository.findOne(id);
=======
    public MediaDTO findOne(Long id) {
        log.debug("Request to get Media : {}", id);
        Media media = mediaRepository.findOne(id);
        MediaDTO mediaDTO = mediaMapper.toDto(media);
        return mediaDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD
    public List<Media> search(String query) {
        log.debug("Request to search Media for query {}", query);
        return StreamSupport
            .stream(mediaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<MediaDTO> search(String query) {
        log.debug("Request to search Media for query {}", query);
        return StreamSupport
            .stream(mediaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(mediaMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

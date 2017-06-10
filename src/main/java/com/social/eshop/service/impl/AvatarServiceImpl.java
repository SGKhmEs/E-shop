package com.social.eshop.service.impl;

import com.social.eshop.service.AvatarService;
import com.social.eshop.domain.Avatar;
import com.social.eshop.repository.AvatarRepository;
import com.social.eshop.repository.search.AvatarSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Avatar.
 */
@Service
@Transactional
public class AvatarServiceImpl implements AvatarService{

    private final Logger log = LoggerFactory.getLogger(AvatarServiceImpl.class);

    private final AvatarRepository avatarRepository;

    private final AvatarSearchRepository avatarSearchRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, AvatarSearchRepository avatarSearchRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarSearchRepository = avatarSearchRepository;
    }

    /**
     * Save a avatar.
     *
     * @param avatar the entity to save
     * @return the persisted entity
     */
    @Override
    public Avatar save(Avatar avatar) {
        log.debug("Request to save Avatar : {}", avatar);
        Avatar result = avatarRepository.save(avatar);
        avatarSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the avatars.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Avatar> findAll() {
        log.debug("Request to get all Avatars");
        return avatarRepository.findAll();
    }

    /**
     *  Get one avatar by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Avatar findOne(Long id) {
        log.debug("Request to get Avatar : {}", id);
        return avatarRepository.findOne(id);
    }

    /**
     *  Delete the  avatar by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Avatar : {}", id);
        avatarRepository.delete(id);
        avatarSearchRepository.delete(id);
    }

    /**
     * Search for the avatar corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Avatar> search(String query) {
        log.debug("Request to search Avatars for query {}", query);
        return StreamSupport
            .stream(avatarSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

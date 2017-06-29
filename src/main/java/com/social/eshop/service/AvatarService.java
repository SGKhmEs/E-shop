package com.social.eshop.service;

import com.social.eshop.service.dto.AvatarDTO;
import java.util.List;

/**
 * Service Interface for managing Avatar.
 */
public interface AvatarService {

    /**
     * Save a avatar.
     *
     * @param avatarDTO the entity to save
     * @return the persisted entity
     */
    AvatarDTO save(AvatarDTO avatarDTO);

    /**
     *  Get all the avatars.
     *
     *  @return the list of entities
     */
    List<AvatarDTO> findAll();

    /**
     *  Get the "id" avatar.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AvatarDTO findOne(Long id);

    /**
     *  Delete the "id" avatar.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the avatar corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<AvatarDTO> search(String query);
}

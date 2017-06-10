package com.social.eshop.service;

import com.social.eshop.domain.Avatar;
import java.util.List;

/**
 * Service Interface for managing Avatar.
 */
public interface AvatarService {

    /**
     * Save a avatar.
     *
     * @param avatar the entity to save
     * @return the persisted entity
     */
    Avatar save(Avatar avatar);

    /**
     *  Get all the avatars.
     *
     *  @return the list of entities
     */
    List<Avatar> findAll();

    /**
     *  Get the "id" avatar.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Avatar findOne(Long id);

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
    List<Avatar> search(String query);
}

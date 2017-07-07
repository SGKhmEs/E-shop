package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Avatar;
=======
import com.social.eshop.service.dto.AvatarDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Avatar.
 */
public interface AvatarService {

    /**
     * Save a avatar.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" avatar.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Avatar findOne(Long id);
=======
    AvatarDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Avatar> search(String query);
=======
    List<AvatarDTO> search(String query);
>>>>>>> with_entities
}

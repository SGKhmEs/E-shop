package com.social.eshop.service;

import com.social.eshop.domain.WishList;
import java.util.List;

/**
 * Service Interface for managing WishList.
 */
public interface WishListService {

    /**
     * Save a wishList.
     *
     * @param wishList the entity to save
     * @return the persisted entity
     */
    WishList save(WishList wishList);

    /**
     *  Get all the wishLists.
     *
     *  @return the list of entities
     */
    List<WishList> findAll();

    /**
     *  Get the "id" wishList.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WishList findOne(Long id);

    /**
     *  Delete the "id" wishList.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the wishList corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<WishList> search(String query);
}

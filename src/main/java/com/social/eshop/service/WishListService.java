package com.social.eshop.service;

import com.social.eshop.service.dto.WishListDTO;
import java.util.List;

/**
 * Service Interface for managing WishList.
 */
public interface WishListService {

    /**
     * Save a wishList.
     *
     * @param wishListDTO the entity to save
     * @return the persisted entity
     */
    WishListDTO save(WishListDTO wishListDTO);

    /**
     *  Get all the wishLists.
     *
     *  @return the list of entities
     */
    List<WishListDTO> findAll();

    /**
     *  Get the "id" wishList.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WishListDTO findOne(Long id);

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
    List<WishListDTO> search(String query);
}

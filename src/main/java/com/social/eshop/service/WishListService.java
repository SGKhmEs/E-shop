package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.WishList;
=======
import com.social.eshop.service.dto.WishListDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing WishList.
 */
public interface WishListService {

    /**
     * Save a wishList.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" wishList.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    WishList findOne(Long id);
=======
    WishListDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<WishList> search(String query);
=======
    List<WishListDTO> search(String query);
>>>>>>> with_entities
}

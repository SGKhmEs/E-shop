package com.social.eshop.service;

import com.social.eshop.domain.Category;
import java.util.List;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService {

    /**
     * Save a category.
     *
     * @param category the entity to save
     * @return the persisted entity
     */
    Category save(Category category);

    /**
     *  Get all the categories.
     *
     *  @return the list of entities
     */
    List<Category> findAll();

    /**
     *  Get the "id" category.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Category findOne(Long id);

    /**
     *  Delete the "id" category.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the category corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<Category> search(String query);
}

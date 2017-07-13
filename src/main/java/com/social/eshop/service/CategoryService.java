package com.social.eshop.service;

import com.social.eshop.service.dto.CategoryDTO;
import java.util.List;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService {

    /**
     * Save a category.
     *
     * @param categoryDTO the entity to save
     * @return the persisted entity
     */
    CategoryDTO save(CategoryDTO categoryDTO);

    /**
     *  Get all the categories.
     *
     *  @return the list of entities
     */
    List<CategoryDTO> findAll();

    /**
     *  Get the "id" category.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CategoryDTO findOne(Long id);

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
    List<CategoryDTO> search(String query);
}

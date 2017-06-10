package com.social.eshop.service;

import com.social.eshop.domain.SubCategory;
import java.util.List;

/**
 * Service Interface for managing SubCategory.
 */
public interface SubCategoryService {

    /**
     * Save a subCategory.
     *
     * @param subCategory the entity to save
     * @return the persisted entity
     */
    SubCategory save(SubCategory subCategory);

    /**
     *  Get all the subCategories.
     *
     *  @return the list of entities
     */
    List<SubCategory> findAll();

    /**
     *  Get the "id" subCategory.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SubCategory findOne(Long id);

    /**
     *  Delete the "id" subCategory.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the subCategory corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<SubCategory> search(String query);
}

package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Category;
=======
import com.social.eshop.service.dto.CategoryDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService {

    /**
     * Save a category.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" category.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Category findOne(Long id);
=======
    CategoryDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<Category> search(String query);
=======
    List<CategoryDTO> search(String query);
>>>>>>> with_entities
}

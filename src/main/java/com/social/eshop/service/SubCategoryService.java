package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.SubCategory;
=======
import com.social.eshop.service.dto.SubCategoryDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing SubCategory.
 */
public interface SubCategoryService {

    /**
     * Save a subCategory.
     *
<<<<<<< HEAD
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
=======
     * @param subCategoryDTO the entity to save
     * @return the persisted entity
     */
    SubCategoryDTO save(SubCategoryDTO subCategoryDTO);

    /**
     *  Get all the subCategories.
     *  
     *  @return the list of entities
     */
    List<SubCategoryDTO> findAll();
>>>>>>> with_entities

    /**
     *  Get the "id" subCategory.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    SubCategory findOne(Long id);
=======
    SubCategoryDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<SubCategory> search(String query);
=======
    List<SubCategoryDTO> search(String query);
>>>>>>> with_entities
}

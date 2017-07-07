package com.social.eshop.service.impl;

import com.social.eshop.service.CategoryService;
import com.social.eshop.domain.Category;
import com.social.eshop.repository.CategoryRepository;
import com.social.eshop.repository.search.CategorySearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.CategoryDTO;
import com.social.eshop.service.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
>>>>>>> with_entities
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
<<<<<<< HEAD

    private final CategoryRepository categoryRepository;

    private final CategorySearchRepository categorySearchRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategorySearchRepository categorySearchRepository) {
        this.categoryRepository = categoryRepository;
=======
    
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final CategorySearchRepository categorySearchRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, CategorySearchRepository categorySearchRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
>>>>>>> with_entities
        this.categorySearchRepository = categorySearchRepository;
    }

    /**
     * Save a category.
     *
<<<<<<< HEAD
     * @param category the entity to save
     * @return the persisted entity
     */
    @Override
    public Category save(Category category) {
        log.debug("Request to save Category : {}", category);
        Category result = categoryRepository.save(category);
        categorySearchRepository.save(result);
=======
     * @param categoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", categoryDTO);
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        CategoryDTO result = categoryMapper.toDto(category);
        categorySearchRepository.save(category);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the categories.
<<<<<<< HEAD
     *
=======
     *  
>>>>>>> with_entities
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Category> findAll() {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll();
=======
    public List<CategoryDTO> findAll() {
        log.debug("Request to get all Categories");
        List<CategoryDTO> result = categoryRepository.findAll().stream()
            .map(categoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one category by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Category findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findOne(id);
=======
    public CategoryDTO findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        Category category = categoryRepository.findOne(id);
        CategoryDTO categoryDTO = categoryMapper.toDto(category);
        return categoryDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  category by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.delete(id);
        categorySearchRepository.delete(id);
    }

    /**
     * Search for the category corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Category> search(String query) {
        log.debug("Request to search Categories for query {}", query);
        return StreamSupport
            .stream(categorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<CategoryDTO> search(String query) {
        log.debug("Request to search Categories for query {}", query);
        return StreamSupport
            .stream(categorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(categoryMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.SubCategoryService;
import com.social.eshop.domain.SubCategory;
import com.social.eshop.repository.SubCategoryRepository;
import com.social.eshop.repository.search.SubCategorySearchRepository;
import com.social.eshop.service.dto.SubCategoryDTO;
import com.social.eshop.service.mapper.SubCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SubCategory.
 */
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{

    private final Logger log = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryMapper subCategoryMapper;

    private final SubCategorySearchRepository subCategorySearchRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper, SubCategorySearchRepository subCategorySearchRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
        this.subCategorySearchRepository = subCategorySearchRepository;
    }

    /**
     * Save a subCategory.
     *
     * @param subCategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SubCategoryDTO save(SubCategoryDTO subCategoryDTO) {
        log.debug("Request to save SubCategory : {}", subCategoryDTO);
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryDTO);
        subCategory = subCategoryRepository.save(subCategory);
        SubCategoryDTO result = subCategoryMapper.toDto(subCategory);
        subCategorySearchRepository.save(subCategory);
        return result;
    }

    /**
     *  Get all the subCategories.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubCategoryDTO> findAll() {
        log.debug("Request to get all SubCategories");
        return subCategoryRepository.findAll().stream()
            .map(subCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one subCategory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SubCategoryDTO findOne(Long id) {
        log.debug("Request to get SubCategory : {}", id);
        SubCategory subCategory = subCategoryRepository.findOne(id);
        return subCategoryMapper.toDto(subCategory);
    }

    /**
     *  Delete the  subCategory by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubCategory : {}", id);
        subCategoryRepository.delete(id);
        subCategorySearchRepository.delete(id);
    }

    /**
     * Search for the subCategory corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubCategoryDTO> search(String query) {
        log.debug("Request to search SubCategories for query {}", query);
        return StreamSupport
            .stream(subCategorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(subCategoryMapper::toDto)
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.TagForProductService;
import com.social.eshop.domain.TagForProduct;
import com.social.eshop.repository.TagForProductRepository;
import com.social.eshop.repository.search.TagForProductSearchRepository;
import com.social.eshop.service.dto.TagForProductDTO;
import com.social.eshop.service.mapper.TagForProductMapper;
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
 * Service Implementation for managing TagForProduct.
 */
@Service
@Transactional
public class TagForProductServiceImpl implements TagForProductService{

    private final Logger log = LoggerFactory.getLogger(TagForProductServiceImpl.class);

    private final TagForProductRepository tagForProductRepository;

    private final TagForProductMapper tagForProductMapper;

    private final TagForProductSearchRepository tagForProductSearchRepository;

    public TagForProductServiceImpl(TagForProductRepository tagForProductRepository, TagForProductMapper tagForProductMapper, TagForProductSearchRepository tagForProductSearchRepository) {
        this.tagForProductRepository = tagForProductRepository;
        this.tagForProductMapper = tagForProductMapper;
        this.tagForProductSearchRepository = tagForProductSearchRepository;
    }

    /**
     * Save a tagForProduct.
     *
     * @param tagForProductDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TagForProductDTO save(TagForProductDTO tagForProductDTO) {
        log.debug("Request to save TagForProduct : {}", tagForProductDTO);
        TagForProduct tagForProduct = tagForProductMapper.toEntity(tagForProductDTO);
        tagForProduct = tagForProductRepository.save(tagForProduct);
        TagForProductDTO result = tagForProductMapper.toDto(tagForProduct);
        tagForProductSearchRepository.save(tagForProduct);
        return result;
    }

    /**
     *  Get all the tagForProducts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TagForProductDTO> findAll() {
        log.debug("Request to get all TagForProducts");
        return tagForProductRepository.findAll().stream()
            .map(tagForProductMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one tagForProduct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TagForProductDTO findOne(Long id) {
        log.debug("Request to get TagForProduct : {}", id);
        TagForProduct tagForProduct = tagForProductRepository.findOne(id);
        return tagForProductMapper.toDto(tagForProduct);
    }

    /**
     *  Delete the  tagForProduct by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TagForProduct : {}", id);
        tagForProductRepository.delete(id);
        tagForProductSearchRepository.delete(id);
    }

    /**
     * Search for the tagForProduct corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TagForProductDTO> search(String query) {
        log.debug("Request to search TagForProducts for query {}", query);
        return StreamSupport
            .stream(tagForProductSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(tagForProductMapper::toDto)
            .collect(Collectors.toList());
    }
}

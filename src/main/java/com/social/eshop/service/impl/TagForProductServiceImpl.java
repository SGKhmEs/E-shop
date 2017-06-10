package com.social.eshop.service.impl;

import com.social.eshop.service.TagForProductService;
import com.social.eshop.domain.TagForProduct;
import com.social.eshop.repository.TagForProductRepository;
import com.social.eshop.repository.search.TagForProductSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final TagForProductSearchRepository tagForProductSearchRepository;

    public TagForProductServiceImpl(TagForProductRepository tagForProductRepository, TagForProductSearchRepository tagForProductSearchRepository) {
        this.tagForProductRepository = tagForProductRepository;
        this.tagForProductSearchRepository = tagForProductSearchRepository;
    }

    /**
     * Save a tagForProduct.
     *
     * @param tagForProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public TagForProduct save(TagForProduct tagForProduct) {
        log.debug("Request to save TagForProduct : {}", tagForProduct);
        TagForProduct result = tagForProductRepository.save(tagForProduct);
        tagForProductSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the tagForProducts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TagForProduct> findAll() {
        log.debug("Request to get all TagForProducts");
        return tagForProductRepository.findAll();
    }

    /**
     *  Get one tagForProduct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TagForProduct findOne(Long id) {
        log.debug("Request to get TagForProduct : {}", id);
        return tagForProductRepository.findOne(id);
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
    public List<TagForProduct> search(String query) {
        log.debug("Request to search TagForProducts for query {}", query);
        return StreamSupport
            .stream(tagForProductSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

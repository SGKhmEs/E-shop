package com.social.eshop.service.impl;

import com.social.eshop.service.TypeService;
import com.social.eshop.domain.Type;
import com.social.eshop.repository.TypeRepository;
import com.social.eshop.repository.search.TypeSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Type.
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService{

    private final Logger log = LoggerFactory.getLogger(TypeServiceImpl.class);

    private final TypeRepository typeRepository;

    private final TypeSearchRepository typeSearchRepository;

    public TypeServiceImpl(TypeRepository typeRepository, TypeSearchRepository typeSearchRepository) {
        this.typeRepository = typeRepository;
        this.typeSearchRepository = typeSearchRepository;
    }

    /**
     * Save a type.
     *
     * @param type the entity to save
     * @return the persisted entity
     */
    @Override
    public Type save(Type type) {
        log.debug("Request to save Type : {}", type);
        Type result = typeRepository.save(type);
        typeSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the types.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Type> findAll() {
        log.debug("Request to get all Types");
        return typeRepository.findAll();
    }

    /**
     *  Get one type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Type findOne(Long id) {
        log.debug("Request to get Type : {}", id);
        return typeRepository.findOne(id);
    }

    /**
     *  Delete the  type by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Type : {}", id);
        typeRepository.delete(id);
        typeSearchRepository.delete(id);
    }

    /**
     * Search for the type corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Type> search(String query) {
        log.debug("Request to search Types for query {}", query);
        return StreamSupport
            .stream(typeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

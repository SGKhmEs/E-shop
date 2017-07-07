package com.social.eshop.service.impl;

import com.social.eshop.service.TypeService;
import com.social.eshop.domain.Type;
import com.social.eshop.repository.TypeRepository;
import com.social.eshop.repository.search.TypeSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.TypeDTO;
import com.social.eshop.service.mapper.TypeMapper;
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
 * Service Implementation for managing Type.
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService{

    private final Logger log = LoggerFactory.getLogger(TypeServiceImpl.class);
<<<<<<< HEAD

    private final TypeRepository typeRepository;

    private final TypeSearchRepository typeSearchRepository;

    public TypeServiceImpl(TypeRepository typeRepository, TypeSearchRepository typeSearchRepository) {
        this.typeRepository = typeRepository;
=======
    
    private final TypeRepository typeRepository;

    private final TypeMapper typeMapper;

    private final TypeSearchRepository typeSearchRepository;

    public TypeServiceImpl(TypeRepository typeRepository, TypeMapper typeMapper, TypeSearchRepository typeSearchRepository) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
>>>>>>> with_entities
        this.typeSearchRepository = typeSearchRepository;
    }

    /**
     * Save a type.
     *
<<<<<<< HEAD
     * @param type the entity to save
     * @return the persisted entity
     */
    @Override
    public Type save(Type type) {
        log.debug("Request to save Type : {}", type);
        Type result = typeRepository.save(type);
        typeSearchRepository.save(result);
=======
     * @param typeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeDTO save(TypeDTO typeDTO) {
        log.debug("Request to save Type : {}", typeDTO);
        Type type = typeMapper.toEntity(typeDTO);
        type = typeRepository.save(type);
        TypeDTO result = typeMapper.toDto(type);
        typeSearchRepository.save(type);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the types.
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
    public List<Type> findAll() {
        log.debug("Request to get all Types");
        return typeRepository.findAll();
=======
    public List<TypeDTO> findAll() {
        log.debug("Request to get all Types");
        List<TypeDTO> result = typeRepository.findAll().stream()
            .map(typeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Type findOne(Long id) {
        log.debug("Request to get Type : {}", id);
        return typeRepository.findOne(id);
=======
    public TypeDTO findOne(Long id) {
        log.debug("Request to get Type : {}", id);
        Type type = typeRepository.findOne(id);
        TypeDTO typeDTO = typeMapper.toDto(type);
        return typeDTO;
>>>>>>> with_entities
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
<<<<<<< HEAD
    public List<Type> search(String query) {
        log.debug("Request to search Types for query {}", query);
        return StreamSupport
            .stream(typeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<TypeDTO> search(String query) {
        log.debug("Request to search Types for query {}", query);
        return StreamSupport
            .stream(typeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(typeMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

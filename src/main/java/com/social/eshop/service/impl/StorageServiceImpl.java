package com.social.eshop.service.impl;

import com.social.eshop.service.StorageService;
import com.social.eshop.domain.Storage;
import com.social.eshop.repository.StorageRepository;
import com.social.eshop.repository.search.StorageSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.StorageDTO;
import com.social.eshop.service.mapper.StorageMapper;
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
 * Service Implementation for managing Storage.
 */
@Service
@Transactional
public class StorageServiceImpl implements StorageService{

    private final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);
<<<<<<< HEAD

    private final StorageRepository storageRepository;

    private final StorageSearchRepository storageSearchRepository;

    public StorageServiceImpl(StorageRepository storageRepository, StorageSearchRepository storageSearchRepository) {
        this.storageRepository = storageRepository;
=======
    
    private final StorageRepository storageRepository;

    private final StorageMapper storageMapper;

    private final StorageSearchRepository storageSearchRepository;

    public StorageServiceImpl(StorageRepository storageRepository, StorageMapper storageMapper, StorageSearchRepository storageSearchRepository) {
        this.storageRepository = storageRepository;
        this.storageMapper = storageMapper;
>>>>>>> with_entities
        this.storageSearchRepository = storageSearchRepository;
    }

    /**
     * Save a storage.
     *
<<<<<<< HEAD
     * @param storage the entity to save
     * @return the persisted entity
     */
    @Override
    public Storage save(Storage storage) {
        log.debug("Request to save Storage : {}", storage);
        Storage result = storageRepository.save(storage);
        storageSearchRepository.save(result);
=======
     * @param storageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StorageDTO save(StorageDTO storageDTO) {
        log.debug("Request to save Storage : {}", storageDTO);
        Storage storage = storageMapper.toEntity(storageDTO);
        storage = storageRepository.save(storage);
        StorageDTO result = storageMapper.toDto(storage);
        storageSearchRepository.save(storage);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the storages.
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
    public List<Storage> findAll() {
        log.debug("Request to get all Storages");
        return storageRepository.findAll();
=======
    public List<StorageDTO> findAll() {
        log.debug("Request to get all Storages");
        List<StorageDTO> result = storageRepository.findAll().stream()
            .map(storageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one storage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Storage findOne(Long id) {
        log.debug("Request to get Storage : {}", id);
        return storageRepository.findOne(id);
=======
    public StorageDTO findOne(Long id) {
        log.debug("Request to get Storage : {}", id);
        Storage storage = storageRepository.findOne(id);
        StorageDTO storageDTO = storageMapper.toDto(storage);
        return storageDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  storage by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Storage : {}", id);
        storageRepository.delete(id);
        storageSearchRepository.delete(id);
    }

    /**
     * Search for the storage corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Storage> search(String query) {
        log.debug("Request to search Storages for query {}", query);
        return StreamSupport
            .stream(storageSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<StorageDTO> search(String query) {
        log.debug("Request to search Storages for query {}", query);
        return StreamSupport
            .stream(storageSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(storageMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

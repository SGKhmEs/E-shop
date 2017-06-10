package com.social.eshop.service.impl;

import com.social.eshop.service.StorageService;
import com.social.eshop.domain.Storage;
import com.social.eshop.repository.StorageRepository;
import com.social.eshop.repository.search.StorageSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final StorageRepository storageRepository;

    private final StorageSearchRepository storageSearchRepository;

    public StorageServiceImpl(StorageRepository storageRepository, StorageSearchRepository storageSearchRepository) {
        this.storageRepository = storageRepository;
        this.storageSearchRepository = storageSearchRepository;
    }

    /**
     * Save a storage.
     *
     * @param storage the entity to save
     * @return the persisted entity
     */
    @Override
    public Storage save(Storage storage) {
        log.debug("Request to save Storage : {}", storage);
        Storage result = storageRepository.save(storage);
        storageSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the storages.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Storage> findAll() {
        log.debug("Request to get all Storages");
        return storageRepository.findAll();
    }

    /**
     *  Get one storage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Storage findOne(Long id) {
        log.debug("Request to get Storage : {}", id);
        return storageRepository.findOne(id);
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
    public List<Storage> search(String query) {
        log.debug("Request to search Storages for query {}", query);
        return StreamSupport
            .stream(storageSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

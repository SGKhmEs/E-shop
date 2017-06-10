package com.social.eshop.service.impl;

import com.social.eshop.service.ManagerService;
import com.social.eshop.domain.Manager;
import com.social.eshop.repository.ManagerRepository;
import com.social.eshop.repository.search.ManagerSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Manager.
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

    private final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    private final ManagerRepository managerRepository;

    private final ManagerSearchRepository managerSearchRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerSearchRepository managerSearchRepository) {
        this.managerRepository = managerRepository;
        this.managerSearchRepository = managerSearchRepository;
    }

    /**
     * Save a manager.
     *
     * @param manager the entity to save
     * @return the persisted entity
     */
    @Override
    public Manager save(Manager manager) {
        log.debug("Request to save Manager : {}", manager);
        Manager result = managerRepository.save(manager);
        managerSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the managers.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Manager> findAll() {
        log.debug("Request to get all Managers");
        return managerRepository.findAll();
    }

    /**
     *  Get one manager by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Manager findOne(Long id) {
        log.debug("Request to get Manager : {}", id);
        return managerRepository.findOne(id);
    }

    /**
     *  Delete the  manager by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Manager : {}", id);
        managerRepository.delete(id);
        managerSearchRepository.delete(id);
    }

    /**
     * Search for the manager corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Manager> search(String query) {
        log.debug("Request to search Managers for query {}", query);
        return StreamSupport
            .stream(managerSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

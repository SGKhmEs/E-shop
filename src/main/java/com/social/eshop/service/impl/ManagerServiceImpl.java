package com.social.eshop.service.impl;

import com.social.eshop.service.ManagerService;
import com.social.eshop.domain.Manager;
import com.social.eshop.repository.ManagerRepository;
import com.social.eshop.repository.search.ManagerSearchRepository;
import com.social.eshop.service.dto.ManagerDTO;
import com.social.eshop.service.mapper.ManagerMapper;
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
 * Service Implementation for managing Manager.
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

    private final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    private final ManagerRepository managerRepository;

    private final ManagerMapper managerMapper;

    private final ManagerSearchRepository managerSearchRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerMapper managerMapper, ManagerSearchRepository managerSearchRepository) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.managerSearchRepository = managerSearchRepository;
    }

    /**
     * Save a manager.
     *
     * @param managerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ManagerDTO save(ManagerDTO managerDTO) {
        log.debug("Request to save Manager : {}", managerDTO);
        Manager manager = managerMapper.toEntity(managerDTO);
        manager = managerRepository.save(manager);
        ManagerDTO result = managerMapper.toDto(manager);
        managerSearchRepository.save(manager);
        return result;
    }

    /**
     *  Get all the managers.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ManagerDTO> findAll() {
        log.debug("Request to get all Managers");
        return managerRepository.findAll().stream()
            .map(managerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one manager by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ManagerDTO findOne(Long id) {
        log.debug("Request to get Manager : {}", id);
        Manager manager = managerRepository.findOne(id);
        return managerMapper.toDto(manager);
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
    public List<ManagerDTO> search(String query) {
        log.debug("Request to search Managers for query {}", query);
        return StreamSupport
            .stream(managerSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(managerMapper::toDto)
            .collect(Collectors.toList());
    }
}

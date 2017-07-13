package com.social.eshop.service.impl;

import com.social.eshop.service.ManagerAccountService;
import com.social.eshop.domain.ManagerAccount;
import com.social.eshop.repository.ManagerAccountRepository;
import com.social.eshop.repository.search.ManagerAccountSearchRepository;
import com.social.eshop.service.dto.ManagerAccountDTO;
import com.social.eshop.service.mapper.ManagerAccountMapper;
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
 * Service Implementation for managing ManagerAccount.
 */
@Service
@Transactional
public class ManagerAccountServiceImpl implements ManagerAccountService{

    private final Logger log = LoggerFactory.getLogger(ManagerAccountServiceImpl.class);

    private final ManagerAccountRepository managerAccountRepository;

    private final ManagerAccountMapper managerAccountMapper;

    private final ManagerAccountSearchRepository managerAccountSearchRepository;

    public ManagerAccountServiceImpl(ManagerAccountRepository managerAccountRepository, ManagerAccountMapper managerAccountMapper, ManagerAccountSearchRepository managerAccountSearchRepository) {
        this.managerAccountRepository = managerAccountRepository;
        this.managerAccountMapper = managerAccountMapper;
        this.managerAccountSearchRepository = managerAccountSearchRepository;
    }

    /**
     * Save a managerAccount.
     *
     * @param managerAccountDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ManagerAccountDTO save(ManagerAccountDTO managerAccountDTO) {
        log.debug("Request to save ManagerAccount : {}", managerAccountDTO);
        ManagerAccount managerAccount = managerAccountMapper.toEntity(managerAccountDTO);
        managerAccount = managerAccountRepository.save(managerAccount);
        ManagerAccountDTO result = managerAccountMapper.toDto(managerAccount);
        managerAccountSearchRepository.save(managerAccount);
        return result;
    }

    /**
     *  Get all the managerAccounts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ManagerAccountDTO> findAll() {
        log.debug("Request to get all ManagerAccounts");
        return managerAccountRepository.findAll().stream()
            .map(managerAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one managerAccount by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ManagerAccountDTO findOne(Long id) {
        log.debug("Request to get ManagerAccount : {}", id);
        ManagerAccount managerAccount = managerAccountRepository.findOne(id);
        return managerAccountMapper.toDto(managerAccount);
    }

    /**
     *  Delete the  managerAccount by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ManagerAccount : {}", id);
        managerAccountRepository.delete(id);
        managerAccountSearchRepository.delete(id);
    }

    /**
     * Search for the managerAccount corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ManagerAccountDTO> search(String query) {
        log.debug("Request to search ManagerAccounts for query {}", query);
        return StreamSupport
            .stream(managerAccountSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(managerAccountMapper::toDto)
            .collect(Collectors.toList());
    }
}

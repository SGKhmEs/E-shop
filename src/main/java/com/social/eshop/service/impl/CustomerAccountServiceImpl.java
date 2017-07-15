package com.social.eshop.service.impl;

import com.social.eshop.service.CustomerAccountService;
import com.social.eshop.domain.CustomerAccount;
import com.social.eshop.repository.CustomerAccountRepository;
import com.social.eshop.repository.search.CustomerAccountSearchRepository;
import com.social.eshop.service.dto.CustomerAccountDTO;
import com.social.eshop.service.mapper.CustomerAccountMapper;
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
 * Service Implementation for managing CustomerAccount.
 */
@Service
@Transactional
public class CustomerAccountServiceImpl implements CustomerAccountService{

    private final Logger log = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);

    private final CustomerAccountRepository customerAccountRepository;

    private final CustomerAccountMapper customerAccountMapper;

    private final CustomerAccountSearchRepository customerAccountSearchRepository;

    public CustomerAccountServiceImpl(CustomerAccountRepository customerAccountRepository, CustomerAccountMapper customerAccountMapper, CustomerAccountSearchRepository customerAccountSearchRepository) {
        this.customerAccountRepository = customerAccountRepository;
        this.customerAccountMapper = customerAccountMapper;
        this.customerAccountSearchRepository = customerAccountSearchRepository;
    }

    /**
     * Save a customerAccount.
     *
     * @param customerAccountDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerAccountDTO save(CustomerAccountDTO customerAccountDTO) {
        log.debug("Request to save CustomerAccount : {}", customerAccountDTO);
        CustomerAccount customerAccount = customerAccountMapper.toEntity(customerAccountDTO);
        customerAccount = customerAccountRepository.save(customerAccount);
        CustomerAccountDTO result = customerAccountMapper.toDto(customerAccount);
        customerAccountSearchRepository.save(customerAccount);
        return result;
    }

    /**
     *  Get all the customerAccounts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerAccountDTO> findAll() {
        log.debug("Request to get all CustomerAccounts");
        return customerAccountRepository.findAll().stream()
            .map(customerAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one customerAccount by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerAccountDTO findOne(Long id) {
        log.debug("Request to get CustomerAccount : {}", id);
        CustomerAccount customerAccount = customerAccountRepository.findOne(id);
        return customerAccountMapper.toDto(customerAccount);
    }

    /**
     *  Delete the  customerAccount by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerAccount : {}", id);
        customerAccountRepository.delete(id);
        customerAccountSearchRepository.delete(id);
    }

    /**
     * Search for the customerAccount corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerAccountDTO> search(String query) {
        log.debug("Request to search CustomerAccounts for query {}", query);
        return StreamSupport
            .stream(customerAccountSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(customerAccountMapper::toDto)
            .collect(Collectors.toList());
    }
}

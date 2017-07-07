package com.social.eshop.service.impl;

import com.social.eshop.service.LoginOptionsService;
import com.social.eshop.domain.LoginOptions;
import com.social.eshop.repository.LoginOptionsRepository;
import com.social.eshop.repository.search.LoginOptionsSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.LoginOptionsDTO;
import com.social.eshop.service.mapper.LoginOptionsMapper;
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
 * Service Implementation for managing LoginOptions.
 */
@Service
@Transactional
public class LoginOptionsServiceImpl implements LoginOptionsService{

    private final Logger log = LoggerFactory.getLogger(LoginOptionsServiceImpl.class);
<<<<<<< HEAD

    private final LoginOptionsRepository loginOptionsRepository;

    private final LoginOptionsSearchRepository loginOptionsSearchRepository;

    public LoginOptionsServiceImpl(LoginOptionsRepository loginOptionsRepository, LoginOptionsSearchRepository loginOptionsSearchRepository) {
        this.loginOptionsRepository = loginOptionsRepository;
=======
    
    private final LoginOptionsRepository loginOptionsRepository;

    private final LoginOptionsMapper loginOptionsMapper;

    private final LoginOptionsSearchRepository loginOptionsSearchRepository;

    public LoginOptionsServiceImpl(LoginOptionsRepository loginOptionsRepository, LoginOptionsMapper loginOptionsMapper, LoginOptionsSearchRepository loginOptionsSearchRepository) {
        this.loginOptionsRepository = loginOptionsRepository;
        this.loginOptionsMapper = loginOptionsMapper;
>>>>>>> with_entities
        this.loginOptionsSearchRepository = loginOptionsSearchRepository;
    }

    /**
     * Save a loginOptions.
     *
<<<<<<< HEAD
     * @param loginOptions the entity to save
     * @return the persisted entity
     */
    @Override
    public LoginOptions save(LoginOptions loginOptions) {
        log.debug("Request to save LoginOptions : {}", loginOptions);
        LoginOptions result = loginOptionsRepository.save(loginOptions);
        loginOptionsSearchRepository.save(result);
=======
     * @param loginOptionsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LoginOptionsDTO save(LoginOptionsDTO loginOptionsDTO) {
        log.debug("Request to save LoginOptions : {}", loginOptionsDTO);
        LoginOptions loginOptions = loginOptionsMapper.toEntity(loginOptionsDTO);
        loginOptions = loginOptionsRepository.save(loginOptions);
        LoginOptionsDTO result = loginOptionsMapper.toDto(loginOptions);
        loginOptionsSearchRepository.save(loginOptions);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the loginOptions.
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
    public List<LoginOptions> findAll() {
        log.debug("Request to get all LoginOptions");
        return loginOptionsRepository.findAll();
=======
    public List<LoginOptionsDTO> findAll() {
        log.debug("Request to get all LoginOptions");
        List<LoginOptionsDTO> result = loginOptionsRepository.findAll().stream()
            .map(loginOptionsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one loginOptions by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public LoginOptions findOne(Long id) {
        log.debug("Request to get LoginOptions : {}", id);
        return loginOptionsRepository.findOne(id);
=======
    public LoginOptionsDTO findOne(Long id) {
        log.debug("Request to get LoginOptions : {}", id);
        LoginOptions loginOptions = loginOptionsRepository.findOne(id);
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(loginOptions);
        return loginOptionsDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  loginOptions by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LoginOptions : {}", id);
        loginOptionsRepository.delete(id);
        loginOptionsSearchRepository.delete(id);
    }

    /**
     * Search for the loginOptions corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<LoginOptions> search(String query) {
        log.debug("Request to search LoginOptions for query {}", query);
        return StreamSupport
            .stream(loginOptionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<LoginOptionsDTO> search(String query) {
        log.debug("Request to search LoginOptions for query {}", query);
        return StreamSupport
            .stream(loginOptionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(loginOptionsMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

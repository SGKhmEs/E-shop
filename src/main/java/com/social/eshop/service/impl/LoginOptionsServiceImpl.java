package com.social.eshop.service.impl;

import com.social.eshop.service.LoginOptionsService;
import com.social.eshop.domain.LoginOptions;
import com.social.eshop.repository.LoginOptionsRepository;
import com.social.eshop.repository.search.LoginOptionsSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final LoginOptionsRepository loginOptionsRepository;

    private final LoginOptionsSearchRepository loginOptionsSearchRepository;

    public LoginOptionsServiceImpl(LoginOptionsRepository loginOptionsRepository, LoginOptionsSearchRepository loginOptionsSearchRepository) {
        this.loginOptionsRepository = loginOptionsRepository;
        this.loginOptionsSearchRepository = loginOptionsSearchRepository;
    }

    /**
     * Save a loginOptions.
     *
     * @param loginOptions the entity to save
     * @return the persisted entity
     */
    @Override
    public LoginOptions save(LoginOptions loginOptions) {
        log.debug("Request to save LoginOptions : {}", loginOptions);
        LoginOptions result = loginOptionsRepository.save(loginOptions);
        loginOptionsSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the loginOptions.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LoginOptions> findAll() {
        log.debug("Request to get all LoginOptions");
        return loginOptionsRepository.findAll();
    }

    /**
     *  Get one loginOptions by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LoginOptions findOne(Long id) {
        log.debug("Request to get LoginOptions : {}", id);
        return loginOptionsRepository.findOne(id);
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
    public List<LoginOptions> search(String query) {
        log.debug("Request to search LoginOptions for query {}", query);
        return StreamSupport
            .stream(loginOptionsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

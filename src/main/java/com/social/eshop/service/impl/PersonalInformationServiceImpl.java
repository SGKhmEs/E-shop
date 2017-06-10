package com.social.eshop.service.impl;

import com.social.eshop.service.PersonalInformationService;
import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.repository.PersonalInformationRepository;
import com.social.eshop.repository.search.PersonalInformationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PersonalInformation.
 */
@Service
@Transactional
public class PersonalInformationServiceImpl implements PersonalInformationService{

    private final Logger log = LoggerFactory.getLogger(PersonalInformationServiceImpl.class);

    private final PersonalInformationRepository personalInformationRepository;

    private final PersonalInformationSearchRepository personalInformationSearchRepository;

    public PersonalInformationServiceImpl(PersonalInformationRepository personalInformationRepository, PersonalInformationSearchRepository personalInformationSearchRepository) {
        this.personalInformationRepository = personalInformationRepository;
        this.personalInformationSearchRepository = personalInformationSearchRepository;
    }

    /**
     * Save a personalInformation.
     *
     * @param personalInformation the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonalInformation save(PersonalInformation personalInformation) {
        log.debug("Request to save PersonalInformation : {}", personalInformation);
        PersonalInformation result = personalInformationRepository.save(personalInformation);
        personalInformationSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the personalInformations.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonalInformation> findAll() {
        log.debug("Request to get all PersonalInformations");
        return personalInformationRepository.findAll();
    }

    /**
     *  Get one personalInformation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PersonalInformation findOne(Long id) {
        log.debug("Request to get PersonalInformation : {}", id);
        return personalInformationRepository.findOne(id);
    }

    /**
     *  Delete the  personalInformation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonalInformation : {}", id);
        personalInformationRepository.delete(id);
        personalInformationSearchRepository.delete(id);
    }

    /**
     * Search for the personalInformation corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonalInformation> search(String query) {
        log.debug("Request to search PersonalInformations for query {}", query);
        return StreamSupport
            .stream(personalInformationSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

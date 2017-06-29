package com.social.eshop.service.impl;

import com.social.eshop.service.PersonalInformationService;
import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.repository.PersonalInformationRepository;
import com.social.eshop.repository.search.PersonalInformationSearchRepository;
import com.social.eshop.service.dto.PersonalInformationDTO;
import com.social.eshop.service.mapper.PersonalInformationMapper;
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
 * Service Implementation for managing PersonalInformation.
 */
@Service
@Transactional
public class PersonalInformationServiceImpl implements PersonalInformationService{

    private final Logger log = LoggerFactory.getLogger(PersonalInformationServiceImpl.class);

    private final PersonalInformationRepository personalInformationRepository;

    private final PersonalInformationMapper personalInformationMapper;

    private final PersonalInformationSearchRepository personalInformationSearchRepository;

    public PersonalInformationServiceImpl(PersonalInformationRepository personalInformationRepository, PersonalInformationMapper personalInformationMapper, PersonalInformationSearchRepository personalInformationSearchRepository) {
        this.personalInformationRepository = personalInformationRepository;
        this.personalInformationMapper = personalInformationMapper;
        this.personalInformationSearchRepository = personalInformationSearchRepository;
    }

    /**
     * Save a personalInformation.
     *
     * @param personalInformationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonalInformationDTO save(PersonalInformationDTO personalInformationDTO) {
        log.debug("Request to save PersonalInformation : {}", personalInformationDTO);
        PersonalInformation personalInformation = personalInformationMapper.toEntity(personalInformationDTO);
        personalInformation = personalInformationRepository.save(personalInformation);
        PersonalInformationDTO result = personalInformationMapper.toDto(personalInformation);
        personalInformationSearchRepository.save(personalInformation);
        return result;
    }

    /**
     *  Get all the personalInformations.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonalInformationDTO> findAll() {
        log.debug("Request to get all PersonalInformations");
        return personalInformationRepository.findAll().stream()
            .map(personalInformationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one personalInformation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PersonalInformationDTO findOne(Long id) {
        log.debug("Request to get PersonalInformation : {}", id);
        PersonalInformation personalInformation = personalInformationRepository.findOne(id);
        return personalInformationMapper.toDto(personalInformation);
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
    public List<PersonalInformationDTO> search(String query) {
        log.debug("Request to search PersonalInformations for query {}", query);
        return StreamSupport
            .stream(personalInformationSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(personalInformationMapper::toDto)
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.ConfirmService;
import com.social.eshop.domain.Confirm;
import com.social.eshop.repository.ConfirmRepository;
import com.social.eshop.repository.search.ConfirmSearchRepository;
import com.social.eshop.service.dto.ConfirmDTO;
import com.social.eshop.service.mapper.ConfirmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Confirm.
 */
@Service
@Transactional
public class ConfirmServiceImpl implements ConfirmService{

    private final Logger log = LoggerFactory.getLogger(ConfirmServiceImpl.class);
    
    private final ConfirmRepository confirmRepository;

    private final ConfirmMapper confirmMapper;

    private final ConfirmSearchRepository confirmSearchRepository;

    public ConfirmServiceImpl(ConfirmRepository confirmRepository, ConfirmMapper confirmMapper, ConfirmSearchRepository confirmSearchRepository) {
        this.confirmRepository = confirmRepository;
        this.confirmMapper = confirmMapper;
        this.confirmSearchRepository = confirmSearchRepository;
    }

    /**
     * Save a confirm.
     *
     * @param confirmDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConfirmDTO save(ConfirmDTO confirmDTO) {
        log.debug("Request to save Confirm : {}", confirmDTO);
        Confirm confirm = confirmMapper.toEntity(confirmDTO);
        confirm = confirmRepository.save(confirm);
        ConfirmDTO result = confirmMapper.toDto(confirm);
        confirmSearchRepository.save(confirm);
        return result;
    }

    /**
     *  Get all the confirms.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConfirmDTO> findAll() {
        log.debug("Request to get all Confirms");
        List<ConfirmDTO> result = confirmRepository.findAll().stream()
            .map(confirmMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one confirm by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConfirmDTO findOne(Long id) {
        log.debug("Request to get Confirm : {}", id);
        Confirm confirm = confirmRepository.findOne(id);
        ConfirmDTO confirmDTO = confirmMapper.toDto(confirm);
        return confirmDTO;
    }

    /**
     *  Delete the  confirm by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Confirm : {}", id);
        confirmRepository.delete(id);
        confirmSearchRepository.delete(id);
    }

    /**
     * Search for the confirm corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConfirmDTO> search(String query) {
        log.debug("Request to search Confirms for query {}", query);
        return StreamSupport
            .stream(confirmSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(confirmMapper::toDto)
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.AddressShippingService;
import com.social.eshop.domain.AddressShipping;
import com.social.eshop.repository.AddressShippingRepository;
import com.social.eshop.repository.search.AddressShippingSearchRepository;
import com.social.eshop.service.dto.AddressShippingDTO;
import com.social.eshop.service.mapper.AddressShippingMapper;
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
 * Service Implementation for managing AddressShipping.
 */
@Service
@Transactional
public class AddressShippingServiceImpl implements AddressShippingService{

    private final Logger log = LoggerFactory.getLogger(AddressShippingServiceImpl.class);

    private final AddressShippingRepository addressShippingRepository;

    private final AddressShippingMapper addressShippingMapper;

    private final AddressShippingSearchRepository addressShippingSearchRepository;

    public AddressShippingServiceImpl(AddressShippingRepository addressShippingRepository, AddressShippingMapper addressShippingMapper, AddressShippingSearchRepository addressShippingSearchRepository) {
        this.addressShippingRepository = addressShippingRepository;
        this.addressShippingMapper = addressShippingMapper;
        this.addressShippingSearchRepository = addressShippingSearchRepository;
    }

    /**
     * Save a addressShipping.
     *
     * @param addressShippingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AddressShippingDTO save(AddressShippingDTO addressShippingDTO) {
        log.debug("Request to save AddressShipping : {}", addressShippingDTO);
        AddressShipping addressShipping = addressShippingMapper.toEntity(addressShippingDTO);
        addressShipping = addressShippingRepository.save(addressShipping);
        AddressShippingDTO result = addressShippingMapper.toDto(addressShipping);
        addressShippingSearchRepository.save(addressShipping);
        return result;
    }

    /**
     *  Get all the addressShippings.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddressShippingDTO> findAll() {
        log.debug("Request to get all AddressShippings");
        return addressShippingRepository.findAll().stream()
            .map(addressShippingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one addressShipping by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AddressShippingDTO findOne(Long id) {
        log.debug("Request to get AddressShipping : {}", id);
        AddressShipping addressShipping = addressShippingRepository.findOne(id);
        return addressShippingMapper.toDto(addressShipping);
    }

    /**
     *  Delete the  addressShipping by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AddressShipping : {}", id);
        addressShippingRepository.delete(id);
        addressShippingSearchRepository.delete(id);
    }

    /**
     * Search for the addressShipping corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddressShippingDTO> search(String query) {
        log.debug("Request to search AddressShippings for query {}", query);
        return StreamSupport
            .stream(addressShippingSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(addressShippingMapper::toDto)
            .collect(Collectors.toList());
    }
}

package com.social.eshop.service.impl;

import com.social.eshop.service.AddressShippingService;
import com.social.eshop.domain.AddressShipping;
import com.social.eshop.repository.AddressShippingRepository;
import com.social.eshop.repository.search.AddressShippingSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final AddressShippingSearchRepository addressShippingSearchRepository;

    public AddressShippingServiceImpl(AddressShippingRepository addressShippingRepository, AddressShippingSearchRepository addressShippingSearchRepository) {
        this.addressShippingRepository = addressShippingRepository;
        this.addressShippingSearchRepository = addressShippingSearchRepository;
    }

    /**
     * Save a addressShipping.
     *
     * @param addressShipping the entity to save
     * @return the persisted entity
     */
    @Override
    public AddressShipping save(AddressShipping addressShipping) {
        log.debug("Request to save AddressShipping : {}", addressShipping);
        AddressShipping result = addressShippingRepository.save(addressShipping);
        addressShippingSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the addressShippings.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddressShipping> findAll() {
        log.debug("Request to get all AddressShippings");
        return addressShippingRepository.findAll();
    }

    /**
     *  Get one addressShipping by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AddressShipping findOne(Long id) {
        log.debug("Request to get AddressShipping : {}", id);
        return addressShippingRepository.findOne(id);
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
    public List<AddressShipping> search(String query) {
        log.debug("Request to search AddressShippings for query {}", query);
        return StreamSupport
            .stream(addressShippingSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

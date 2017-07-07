package com.social.eshop.service.impl;

import com.social.eshop.service.AddressService;
import com.social.eshop.domain.Address;
import com.social.eshop.repository.AddressRepository;
import com.social.eshop.repository.search.AddressSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.AddressDTO;
import com.social.eshop.service.mapper.AddressMapper;
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
 * Service Implementation for managing Address.
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
<<<<<<< HEAD

    private final AddressRepository addressRepository;

    private final AddressSearchRepository addressSearchRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressSearchRepository addressSearchRepository) {
        this.addressRepository = addressRepository;
=======
    
    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    private final AddressSearchRepository addressSearchRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, AddressSearchRepository addressSearchRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
>>>>>>> with_entities
        this.addressSearchRepository = addressSearchRepository;
    }

    /**
     * Save a address.
     *
<<<<<<< HEAD
     * @param address the entity to save
     * @return the persisted entity
     */
    @Override
    public Address save(Address address) {
        log.debug("Request to save Address : {}", address);
        Address result = addressRepository.save(address);
        addressSearchRepository.save(result);
=======
     * @param addressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);
        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        AddressDTO result = addressMapper.toDto(address);
        addressSearchRepository.save(address);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the addresses.
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
    public List<Address> findAll() {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll();
=======
    public List<AddressDTO> findAll() {
        log.debug("Request to get all Addresses");
        List<AddressDTO> result = addressRepository.findAll().stream()
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one address by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Address findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findOne(id);
=======
    public AddressDTO findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        Address address = addressRepository.findOne(id);
        AddressDTO addressDTO = addressMapper.toDto(address);
        return addressDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  address by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.delete(id);
        addressSearchRepository.delete(id);
    }

    /**
     * Search for the address corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<Address> search(String query) {
        log.debug("Request to search Addresses for query {}", query);
        return StreamSupport
            .stream(addressSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<AddressDTO> search(String query) {
        log.debug("Request to search Addresses for query {}", query);
        return StreamSupport
            .stream(addressSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(addressMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

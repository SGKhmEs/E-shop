package com.social.eshop.service.impl;

import com.social.eshop.service.WishListService;
import com.social.eshop.domain.WishList;
import com.social.eshop.repository.WishListRepository;
import com.social.eshop.repository.search.WishListSearchRepository;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import com.social.eshop.service.dto.WishListDTO;
import com.social.eshop.service.mapper.WishListMapper;
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
 * Service Implementation for managing WishList.
 */
@Service
@Transactional
public class WishListServiceImpl implements WishListService{

    private final Logger log = LoggerFactory.getLogger(WishListServiceImpl.class);
<<<<<<< HEAD

    private final WishListRepository wishListRepository;

    private final WishListSearchRepository wishListSearchRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, WishListSearchRepository wishListSearchRepository) {
        this.wishListRepository = wishListRepository;
=======
    
    private final WishListRepository wishListRepository;

    private final WishListMapper wishListMapper;

    private final WishListSearchRepository wishListSearchRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, WishListMapper wishListMapper, WishListSearchRepository wishListSearchRepository) {
        this.wishListRepository = wishListRepository;
        this.wishListMapper = wishListMapper;
>>>>>>> with_entities
        this.wishListSearchRepository = wishListSearchRepository;
    }

    /**
     * Save a wishList.
     *
<<<<<<< HEAD
     * @param wishList the entity to save
     * @return the persisted entity
     */
    @Override
    public WishList save(WishList wishList) {
        log.debug("Request to save WishList : {}", wishList);
        WishList result = wishListRepository.save(wishList);
        wishListSearchRepository.save(result);
=======
     * @param wishListDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WishListDTO save(WishListDTO wishListDTO) {
        log.debug("Request to save WishList : {}", wishListDTO);
        WishList wishList = wishListMapper.toEntity(wishListDTO);
        wishList = wishListRepository.save(wishList);
        WishListDTO result = wishListMapper.toDto(wishList);
        wishListSearchRepository.save(wishList);
>>>>>>> with_entities
        return result;
    }

    /**
     *  Get all the wishLists.
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
    public List<WishList> findAll() {
        log.debug("Request to get all WishLists");
        return wishListRepository.findAll();
=======
    public List<WishListDTO> findAll() {
        log.debug("Request to get all WishLists");
        List<WishListDTO> result = wishListRepository.findAll().stream()
            .map(wishListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
>>>>>>> with_entities
    }

    /**
     *  Get one wishList by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public WishList findOne(Long id) {
        log.debug("Request to get WishList : {}", id);
        return wishListRepository.findOne(id);
=======
    public WishListDTO findOne(Long id) {
        log.debug("Request to get WishList : {}", id);
        WishList wishList = wishListRepository.findOne(id);
        WishListDTO wishListDTO = wishListMapper.toDto(wishList);
        return wishListDTO;
>>>>>>> with_entities
    }

    /**
     *  Delete the  wishList by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WishList : {}", id);
        wishListRepository.delete(id);
        wishListSearchRepository.delete(id);
    }

    /**
     * Search for the wishList corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public List<WishList> search(String query) {
        log.debug("Request to search WishLists for query {}", query);
        return StreamSupport
            .stream(wishListSearchRepository.search(queryStringQuery(query)).spliterator(), false)
=======
    public List<WishListDTO> search(String query) {
        log.debug("Request to search WishLists for query {}", query);
        return StreamSupport
            .stream(wishListSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(wishListMapper::toDto)
>>>>>>> with_entities
            .collect(Collectors.toList());
    }
}

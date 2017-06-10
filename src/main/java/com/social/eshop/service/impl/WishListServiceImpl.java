package com.social.eshop.service.impl;

import com.social.eshop.service.WishListService;
import com.social.eshop.domain.WishList;
import com.social.eshop.repository.WishListRepository;
import com.social.eshop.repository.search.WishListSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final WishListRepository wishListRepository;

    private final WishListSearchRepository wishListSearchRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, WishListSearchRepository wishListSearchRepository) {
        this.wishListRepository = wishListRepository;
        this.wishListSearchRepository = wishListSearchRepository;
    }

    /**
     * Save a wishList.
     *
     * @param wishList the entity to save
     * @return the persisted entity
     */
    @Override
    public WishList save(WishList wishList) {
        log.debug("Request to save WishList : {}", wishList);
        WishList result = wishListRepository.save(wishList);
        wishListSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the wishLists.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WishList> findAll() {
        log.debug("Request to get all WishLists");
        return wishListRepository.findAll();
    }

    /**
     *  Get one wishList by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WishList findOne(Long id) {
        log.debug("Request to get WishList : {}", id);
        return wishListRepository.findOne(id);
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
    public List<WishList> search(String query) {
        log.debug("Request to search WishLists for query {}", query);
        return StreamSupport
            .stream(wishListSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}

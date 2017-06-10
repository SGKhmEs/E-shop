package com.social.eshop.repository;

import com.social.eshop.domain.WishList;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the WishList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    
}

package com.social.eshop.repository;

import com.social.eshop.domain.LoginOptions;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LoginOptions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoginOptionsRepository extends JpaRepository<LoginOptions,Long> {
    
}

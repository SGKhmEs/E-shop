package com.social.eshop.repository;

import com.social.eshop.domain.Options;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Options entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OptionsRepository extends JpaRepository<Options,Long> {
    
}

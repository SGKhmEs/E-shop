package com.social.eshop.repository;

import com.social.eshop.domain.StaticPages;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StaticPages entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StaticPagesRepository extends JpaRepository<StaticPages,Long> {
    
}

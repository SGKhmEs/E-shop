package com.social.eshop.repository;

import com.social.eshop.domain.Seen;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Seen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeenRepository extends JpaRepository<Seen,Long> {
    
}

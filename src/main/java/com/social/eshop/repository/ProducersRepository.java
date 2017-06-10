package com.social.eshop.repository;

import com.social.eshop.domain.Producers;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Producers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProducersRepository extends JpaRepository<Producers,Long> {
    
}

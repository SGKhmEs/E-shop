package com.social.eshop.repository;

import com.social.eshop.domain.Value;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Value entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValueRepository extends JpaRepository<Value,Long> {

}

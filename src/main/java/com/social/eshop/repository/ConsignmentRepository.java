package com.social.eshop.repository;

import com.social.eshop.domain.Consignment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Consignment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsignmentRepository extends JpaRepository<Consignment,Long> {
    
}

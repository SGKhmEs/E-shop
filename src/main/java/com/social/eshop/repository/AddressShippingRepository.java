package com.social.eshop.repository;

import com.social.eshop.domain.AddressShipping;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AddressShipping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressShippingRepository extends JpaRepository<AddressShipping,Long> {
    
}

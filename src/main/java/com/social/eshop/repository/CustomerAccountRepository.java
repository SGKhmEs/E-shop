package com.social.eshop.repository;

import com.social.eshop.domain.CustomerAccount;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount,Long> {
    
}

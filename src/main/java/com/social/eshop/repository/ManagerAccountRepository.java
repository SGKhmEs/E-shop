package com.social.eshop.repository;

import com.social.eshop.domain.ManagerAccount;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ManagerAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManagerAccountRepository extends JpaRepository<ManagerAccount,Long> {
    
}

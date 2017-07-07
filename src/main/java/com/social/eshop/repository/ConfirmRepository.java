package com.social.eshop.repository;

import com.social.eshop.domain.Confirm;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Confirm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfirmRepository extends JpaRepository<Confirm,Long> {

}

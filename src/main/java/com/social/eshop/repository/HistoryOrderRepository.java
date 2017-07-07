package com.social.eshop.repository;

import com.social.eshop.domain.HistoryOrder;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the HistoryOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoryOrderRepository extends JpaRepository<HistoryOrder,Long> {

}

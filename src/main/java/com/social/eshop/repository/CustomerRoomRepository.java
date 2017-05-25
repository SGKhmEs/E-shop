package com.social.eshop.repository;

import com.social.eshop.domain.CustomerRoom;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerRoom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRoomRepository extends JpaRepository<CustomerRoom,Long> {

}

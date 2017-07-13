package com.social.eshop.repository;

import com.social.eshop.domain.Avatar;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Avatar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    
}

package com.social.eshop.repository;

import com.social.eshop.domain.PersonalInformation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PersonalInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation,Long> {
    
}

package com.social.eshop.service;

import com.social.eshop.domain.PersonalInformation;
import java.util.List;

/**
 * Service Interface for managing PersonalInformation.
 */
public interface PersonalInformationService {

    /**
     * Save a personalInformation.
     *
     * @param personalInformation the entity to save
     * @return the persisted entity
     */
    PersonalInformation save(PersonalInformation personalInformation);

    /**
     *  Get all the personalInformations.
     *
     *  @return the list of entities
     */
    List<PersonalInformation> findAll();

    /**
     *  Get the "id" personalInformation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PersonalInformation findOne(Long id);

    /**
     *  Delete the "id" personalInformation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the personalInformation corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<PersonalInformation> search(String query);
}

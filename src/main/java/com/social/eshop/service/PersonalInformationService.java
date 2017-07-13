package com.social.eshop.service;

import com.social.eshop.service.dto.PersonalInformationDTO;
import java.util.List;

/**
 * Service Interface for managing PersonalInformation.
 */
public interface PersonalInformationService {

    /**
     * Save a personalInformation.
     *
     * @param personalInformationDTO the entity to save
     * @return the persisted entity
     */
    PersonalInformationDTO save(PersonalInformationDTO personalInformationDTO);

    /**
     *  Get all the personalInformations.
     *
     *  @return the list of entities
     */
    List<PersonalInformationDTO> findAll();

    /**
     *  Get the "id" personalInformation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PersonalInformationDTO findOne(Long id);

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
    List<PersonalInformationDTO> search(String query);
}

package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.PersonalInformation;
=======
import com.social.eshop.service.dto.PersonalInformationDTO;
>>>>>>> with_entities
import java.util.List;

/**
 * Service Interface for managing PersonalInformation.
 */
public interface PersonalInformationService {

    /**
     * Save a personalInformation.
     *
<<<<<<< HEAD
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
=======
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
>>>>>>> with_entities

    /**
     *  Get the "id" personalInformation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    PersonalInformation findOne(Long id);
=======
    PersonalInformationDTO findOne(Long id);
>>>>>>> with_entities

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
<<<<<<< HEAD
    List<PersonalInformation> search(String query);
=======
    List<PersonalInformationDTO> search(String query);
>>>>>>> with_entities
}

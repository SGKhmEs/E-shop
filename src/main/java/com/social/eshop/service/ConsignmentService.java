package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Consignment;
import java.util.List;
=======
import com.social.eshop.service.dto.ConsignmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> with_entities

/**
 * Service Interface for managing Consignment.
 */
public interface ConsignmentService {

    /**
     * Save a consignment.
     *
<<<<<<< HEAD
     * @param consignment the entity to save
     * @return the persisted entity
     */
    Consignment save(Consignment consignment);

    /**
     *  Get all the consignments.
     *
     *  @return the list of entities
     */
    List<Consignment> findAll();
=======
     * @param consignmentDTO the entity to save
     * @return the persisted entity
     */
    ConsignmentDTO save(ConsignmentDTO consignmentDTO);

    /**
     *  Get all the consignments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ConsignmentDTO> findAll(Pageable pageable);
>>>>>>> with_entities

    /**
     *  Get the "id" consignment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
<<<<<<< HEAD
    Consignment findOne(Long id);
=======
    ConsignmentDTO findOne(Long id);
>>>>>>> with_entities

    /**
     *  Delete the "id" consignment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the consignment corresponding to the query.
     *
     *  @param query the query of the search
     *  
<<<<<<< HEAD
     *  @return the list of entities
     */
    List<Consignment> search(String query);
=======
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ConsignmentDTO> search(String query, Pageable pageable);
>>>>>>> with_entities
}

package com.social.eshop.service;

import com.social.eshop.service.dto.ConsignmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Consignment.
 */
public interface ConsignmentService {

    /**
     * Save a consignment.
     *
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

    /**
     *  Get the "id" consignment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ConsignmentDTO findOne(Long id);

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
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ConsignmentDTO> search(String query, Pageable pageable);
}

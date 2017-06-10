package com.social.eshop.service;

import com.social.eshop.domain.Consignment;
import java.util.List;

/**
 * Service Interface for managing Consignment.
 */
public interface ConsignmentService {

    /**
     * Save a consignment.
     *
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

    /**
     *  Get the "id" consignment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Consignment findOne(Long id);

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
     *  @return the list of entities
     */
    List<Consignment> search(String query);
}

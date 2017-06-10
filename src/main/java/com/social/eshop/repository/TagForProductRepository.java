package com.social.eshop.repository;

import com.social.eshop.domain.TagForProduct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TagForProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagForProductRepository extends JpaRepository<TagForProduct,Long> {
    
}

package com.social.eshop.repository;

import com.social.eshop.domain.Products;
import com.social.eshop.domain.TagForProduct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the TagForProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagForProductRepository extends JpaRepository<TagForProduct,Long> {

    @Query("SELECT pt.products FROM TagForProduct pt where tag_id =?1 ")
    List<Products> findByTagId(Long id);

}

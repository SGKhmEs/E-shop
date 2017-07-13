package com.social.eshop.repository;

import com.social.eshop.domain.Products;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {

    @Query("SELECT pc FROM Products pc where SubCategory_id =?1 ")
    List<Products> findByCategoryId(Long id);

}

package com.social.eshop.repository;

import com.social.eshop.domain.Category;
import com.social.eshop.domain.Products;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT pc.products FROM Category pc where category_id =?1 ")
    List<Products> findByCategoryId(Long id);

}

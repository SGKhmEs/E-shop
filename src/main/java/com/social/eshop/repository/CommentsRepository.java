package com.social.eshop.repository;

import com.social.eshop.domain.Comments;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Comments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("FROM Comments where products_id =?1")
    public List<Comments> findByProductsId(Long id);
}

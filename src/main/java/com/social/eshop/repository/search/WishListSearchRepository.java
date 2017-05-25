package com.social.eshop.repository.search;

import com.social.eshop.domain.WishList;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the WishList entity.
 */
public interface WishListSearchRepository extends ElasticsearchRepository<WishList, Long> {
}

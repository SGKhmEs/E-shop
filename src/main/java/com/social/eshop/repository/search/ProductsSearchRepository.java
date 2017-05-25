package com.social.eshop.repository.search;

import com.social.eshop.domain.Products;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Products entity.
 */
public interface ProductsSearchRepository extends ElasticsearchRepository<Products, Long> {
}

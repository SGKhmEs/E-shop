package com.social.eshop.repository.search;

import com.social.eshop.domain.SubCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SubCategory entity.
 */
public interface SubCategorySearchRepository extends ElasticsearchRepository<SubCategory, Long> {
}

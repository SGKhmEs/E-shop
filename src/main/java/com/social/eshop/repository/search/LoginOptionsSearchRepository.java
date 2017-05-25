package com.social.eshop.repository.search;

import com.social.eshop.domain.LoginOptions;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LoginOptions entity.
 */
public interface LoginOptionsSearchRepository extends ElasticsearchRepository<LoginOptions, Long> {
}

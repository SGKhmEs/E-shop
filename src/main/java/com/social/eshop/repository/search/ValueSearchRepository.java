package com.social.eshop.repository.search;

import com.social.eshop.domain.Value;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Value entity.
 */
public interface ValueSearchRepository extends ElasticsearchRepository<Value, Long> {
}

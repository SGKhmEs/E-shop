package com.social.eshop.repository.search;

import com.social.eshop.domain.StaticPages;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StaticPages entity.
 */
public interface StaticPagesSearchRepository extends ElasticsearchRepository<StaticPages, Long> {
}

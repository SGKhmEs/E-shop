package com.social.eshop.repository.search;

import com.social.eshop.domain.Producers;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Producers entity.
 */
public interface ProducersSearchRepository extends ElasticsearchRepository<Producers, Long> {
}

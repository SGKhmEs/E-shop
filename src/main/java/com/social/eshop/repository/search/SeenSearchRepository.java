package com.social.eshop.repository.search;

import com.social.eshop.domain.Seen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Seen entity.
 */
public interface SeenSearchRepository extends ElasticsearchRepository<Seen, Long> {
}

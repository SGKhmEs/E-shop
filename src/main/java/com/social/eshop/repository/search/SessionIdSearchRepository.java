package com.social.eshop.repository.search;

import com.social.eshop.domain.SessionId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SessionId entity.
 */
public interface SessionIdSearchRepository extends ElasticsearchRepository<SessionId, Long> {
}

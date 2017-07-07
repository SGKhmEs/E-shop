package com.social.eshop.repository.search;

import com.social.eshop.domain.Manager;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Manager entity.
 */
public interface ManagerSearchRepository extends ElasticsearchRepository<Manager, Long> {
}

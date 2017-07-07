package com.social.eshop.repository.search;

import com.social.eshop.domain.HistoryOrder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HistoryOrder entity.
 */
public interface HistoryOrderSearchRepository extends ElasticsearchRepository<HistoryOrder, Long> {
}

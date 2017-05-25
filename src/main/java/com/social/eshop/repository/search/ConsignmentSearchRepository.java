package com.social.eshop.repository.search;

import com.social.eshop.domain.Consignment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Consignment entity.
 */
public interface ConsignmentSearchRepository extends ElasticsearchRepository<Consignment, Long> {
}

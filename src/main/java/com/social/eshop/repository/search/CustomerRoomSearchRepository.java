package com.social.eshop.repository.search;

import com.social.eshop.domain.CustomerRoom;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerRoom entity.
 */
public interface CustomerRoomSearchRepository extends ElasticsearchRepository<CustomerRoom, Long> {
}

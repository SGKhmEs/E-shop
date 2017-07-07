package com.social.eshop.repository.search;

import com.social.eshop.domain.Confirm;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Confirm entity.
 */
public interface ConfirmSearchRepository extends ElasticsearchRepository<Confirm, Long> {
}

package com.social.eshop.repository.search;

import com.social.eshop.domain.CustomerAccount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerAccount entity.
 */
public interface CustomerAccountSearchRepository extends ElasticsearchRepository<CustomerAccount, Long> {
}

package com.social.eshop.repository.search;

import com.social.eshop.domain.AddressShipping;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AddressShipping entity.
 */
public interface AddressShippingSearchRepository extends ElasticsearchRepository<AddressShipping, Long> {
}

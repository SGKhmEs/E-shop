package com.social.eshop.repository.search;

import com.social.eshop.domain.ProductInBucket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ProductInBucket entity.
 */
public interface ProductInBucketSearchRepository extends ElasticsearchRepository<ProductInBucket, Long> {
}

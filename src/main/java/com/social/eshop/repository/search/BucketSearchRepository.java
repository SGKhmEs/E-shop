package com.social.eshop.repository.search;

import com.social.eshop.domain.Bucket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Bucket entity.
 */
public interface BucketSearchRepository extends ElasticsearchRepository<Bucket, Long> {
}

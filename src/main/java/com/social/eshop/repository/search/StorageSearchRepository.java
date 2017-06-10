package com.social.eshop.repository.search;

import com.social.eshop.domain.Storage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Storage entity.
 */
public interface StorageSearchRepository extends ElasticsearchRepository<Storage, Long> {
}

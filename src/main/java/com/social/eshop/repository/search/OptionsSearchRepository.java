package com.social.eshop.repository.search;

import com.social.eshop.domain.Options;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Options entity.
 */
public interface OptionsSearchRepository extends ElasticsearchRepository<Options, Long> {
}

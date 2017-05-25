package com.social.eshop.repository.search;

import com.social.eshop.domain.Media;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Media entity.
 */
public interface MediaSearchRepository extends ElasticsearchRepository<Media, Long> {
}

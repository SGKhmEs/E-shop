package com.social.eshop.repository.search;

import com.social.eshop.domain.Tags;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Tags entity.
 */
public interface TagsSearchRepository extends ElasticsearchRepository<Tags, Long> {
}

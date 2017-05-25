package com.social.eshop.repository.search;

import com.social.eshop.domain.Avatar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Avatar entity.
 */
public interface AvatarSearchRepository extends ElasticsearchRepository<Avatar, Long> {
}

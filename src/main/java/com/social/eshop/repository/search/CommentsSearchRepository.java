package com.social.eshop.repository.search;

import com.social.eshop.domain.Comments;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Comments entity.
 */
public interface CommentsSearchRepository extends ElasticsearchRepository<Comments, Long> {
}

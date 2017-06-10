package com.social.eshop.repository.search;

import com.social.eshop.domain.TagForProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TagForProduct entity.
 */
public interface TagForProductSearchRepository extends ElasticsearchRepository<TagForProduct, Long> {
}

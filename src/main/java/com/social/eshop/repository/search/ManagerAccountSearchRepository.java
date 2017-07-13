package com.social.eshop.repository.search;

import com.social.eshop.domain.ManagerAccount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ManagerAccount entity.
 */
public interface ManagerAccountSearchRepository extends ElasticsearchRepository<ManagerAccount, Long> {
}

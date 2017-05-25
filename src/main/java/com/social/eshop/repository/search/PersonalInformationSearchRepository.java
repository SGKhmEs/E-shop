package com.social.eshop.repository.search;

import com.social.eshop.domain.PersonalInformation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PersonalInformation entity.
 */
public interface PersonalInformationSearchRepository extends ElasticsearchRepository<PersonalInformation, Long> {
}

package com.playtech.librioniq.repository.search;

import com.playtech.librioniq.domain.Answer;
import com.playtech.librioniq.domain.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Answer entity.
 */
public interface AnswerSearchRepository extends ElasticsearchRepository<Answer, Long> {
}

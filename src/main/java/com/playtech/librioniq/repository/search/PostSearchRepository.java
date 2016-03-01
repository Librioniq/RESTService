package com.playtech.librioniq.repository.search;

import com.playtech.librioniq.domain.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Post entity.
 */
public interface PostSearchRepository extends ElasticsearchRepository<Post, Long> {
}

package com.playtech.librioniq.service.impl;

import com.playtech.librioniq.service.PostService;
import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.repository.PostRepository;
import com.playtech.librioniq.repository.search.PostSearchRepository;
import com.playtech.librioniq.web.rest.dto.PostDTO;
import com.playtech.librioniq.web.rest.mapper.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Post.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService{

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    
    @Inject
    private PostRepository postRepository;
    
    @Inject
    private PostMapper postMapper;
    
    @Inject
    private PostSearchRepository postSearchRepository;
    
    /**
     * Save a post.
     * @return the persisted entity
     */
    public PostDTO save(PostDTO postDTO) {
        log.debug("Request to save Post : {}", postDTO);
        Post post = postMapper.postDTOToPost(postDTO);
        post = postRepository.save(post);
        PostDTO result = postMapper.postToPostDTO(post);
        postSearchRepository.save(post);
        return result;
    }

    /**
     *  get all the posts.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PostDTO> findAll() {
        log.debug("Request to get all Posts");
        List<PostDTO> result = postRepository.findAll().stream()
            .map(postMapper::postToPostDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one post by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PostDTO findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        Post post = postRepository.findOne(id);
        PostDTO postDTO = postMapper.postToPostDTO(post);
        return postDTO;
    }

    /**
     *  delete the  post by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.delete(id);
        postSearchRepository.delete(id);
    }

    /**
     * search for the post corresponding
     * to the query.
     */
    @Transactional(readOnly = true) 
    public List<PostDTO> search(String query) {
        
        log.debug("REST request to search Posts for query {}", query);
        return StreamSupport
            .stream(postSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(postMapper::postToPostDTO)
            .collect(Collectors.toList());
    }
}

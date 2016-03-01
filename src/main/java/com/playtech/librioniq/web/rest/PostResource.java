package com.playtech.librioniq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.service.PostService;
import com.playtech.librioniq.web.rest.util.HeaderUtil;
import com.playtech.librioniq.web.rest.dto.PostDTO;
import com.playtech.librioniq.web.rest.mapper.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Post.
 */
@RestController
@RequestMapping("/api")
public class PostResource {

    private final Logger log = LoggerFactory.getLogger(PostResource.class);
        
    @Inject
    private PostService postService;
    
    @Inject
    private PostMapper postMapper;
    
    /**
     * POST  /posts -> Create a new post.
     */
    @RequestMapping(value = "/posts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) throws URISyntaxException {
        log.debug("REST request to save Post : {}", postDTO);
        if (postDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("post", "idexists", "A new post cannot already have an ID")).body(null);
        }
        PostDTO result = postService.save(postDTO);
        return ResponseEntity.created(new URI("/api/posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("post", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /posts -> Updates an existing post.
     */
    @RequestMapping(value = "/posts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO) throws URISyntaxException {
        log.debug("REST request to update Post : {}", postDTO);
        if (postDTO.getId() == null) {
            return createPost(postDTO);
        }
        PostDTO result = postService.save(postDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("post", postDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /posts -> get all the posts.
     */
    @RequestMapping(value = "/posts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<PostDTO> getAllPosts() {
        log.debug("REST request to get all Posts");
        return postService.findAll();
            }

    /**
     * GET  /posts/:id -> get the "id" post.
     */
    @RequestMapping(value = "/posts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        PostDTO postDTO = postService.findOne(id);
        return Optional.ofNullable(postDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /posts/:id -> delete the "id" post.
     */
    @RequestMapping(value = "/posts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post : {}", id);
        postService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("post", id.toString())).build();
    }

    /**
     * SEARCH  /_search/posts/:query -> search for the post corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/posts/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PostDTO> searchPosts(@PathVariable String query) {
        log.debug("Request to search Posts for query {}", query);
        return postService.search(query);
    }
}

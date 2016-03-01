package com.playtech.librioniq.service;

import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.web.rest.dto.PostDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Post.
 */
public interface PostService {

    /**
     * Save a post.
     * @return the persisted entity
     */
    public PostDTO save(PostDTO postDTO);

    /**
     *  get all the posts.
     *  @return the list of entities
     */
    public List<PostDTO> findAll();

    /**
     *  get the "id" post.
     *  @return the entity
     */
    public PostDTO findOne(Long id);

    /**
     *  delete the "id" post.
     */
    public void delete(Long id);

    /**
     * search for the post corresponding
     * to the query.
     */
    public List<PostDTO> search(String query);
}

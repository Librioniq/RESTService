package com.playtech.librioniq.service;

import com.playtech.librioniq.web.rest.dto.CommentDTO;

import java.util.List;

public interface CommentService extends PostService{

    /**
     * Save comment
     * @param commentDTO
     * @return
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * Find all comments for post
     * @param postId
     * @return
     */
    List<CommentDTO> findAllCommentsForPost(Long postId);
}

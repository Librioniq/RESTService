package com.playtech.librioniq.service;

import com.playtech.librioniq.web.rest.dto.QuestionDTO;

import java.util.List;

/**
 * Service Interface for managing Post.
 */
public interface CommentService {

    /**
     * Save a question.
     *
     * @return the persisted entity
     */
    public QuestionDTO save(QuestionDTO questionDTO);

    /**
     * get all the questions.
     *
     * @return the list of entities
     */
    public List<QuestionDTO> findAll();

    /**
     * get the "id" question.
     *
     * @return the entity
     */
    public QuestionDTO findOne(Long id);

    /**
     * delete the "id" post.
     */
    public void delete(Long id);

    /**
     * search for the question corresponding
     * to the query.
     */
    public List<QuestionDTO> search(String query);
}

package com.playtech.librioniq.service;

import com.playtech.librioniq.web.rest.dto.AnswerDTO;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;

import java.util.List;

/**
 * Service Interface for managing Post.
 */
public interface AnswerService {

    /**
     * Save a question.
     *
     * @return the persisted entity
     */
    public AnswerDTO save(Long questionId, AnswerDTO answerDTO);

    /**
     * get all the questions.
     *
     * @return the list of entities
     */
    public List<AnswerDTO> findAll();

    /**
     * get the "id" question.
     *
     * @return the entity
     */
    public AnswerDTO findOne(Long id);

    /**
     * delete the "id" post.
     */
    public void delete(Long id);

    /**
     * search for the question corresponding
     * to the query.
     */
    public List<AnswerDTO> search(String query);
}

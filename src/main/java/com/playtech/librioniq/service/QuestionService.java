package com.playtech.librioniq.service;

import com.playtech.librioniq.web.rest.dto.QuestionDTO;

import java.util.List;

public interface QuestionService extends PostService {
    /**
     * Save question
     * @param questionDTO
     * @return
     */
    QuestionDTO save(QuestionDTO questionDTO);

    /**
     * Find all questions
     * @return
     */
    List<QuestionDTO> findAllQuestions();

    /**
     * Find question by id
     * @param id
     * @return
     */
    QuestionDTO findQuestion(Long id);

}

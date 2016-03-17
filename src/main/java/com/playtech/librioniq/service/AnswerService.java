package com.playtech.librioniq.service;

import com.playtech.librioniq.web.rest.dto.AnswerDTO;

import java.util.List;

public interface AnswerService extends PostService{
    /**
     * Save answer
     * @param answerDTO
     * @return
     */
    AnswerDTO save(AnswerDTO answerDTO);

    /**
     * Find all answers for question
     * @return
     */
    List<AnswerDTO> findAllAnswersForQuestion(Long questionId);
}

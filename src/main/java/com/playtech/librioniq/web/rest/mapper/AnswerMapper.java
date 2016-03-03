package com.playtech.librioniq.web.rest.mapper;

import com.playtech.librioniq.domain.Answer;
import com.playtech.librioniq.web.rest.dto.AnswerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entities Post, Question and its DTO QuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnswerMapper {
    AnswerDTO answerToAnswerDTO(Answer answer);

    Answer answerDTOToAnswer(AnswerDTO answerDTO);
}

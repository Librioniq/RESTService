package com.playtech.librioniq.web.rest.mapper;

import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.domain.Question;
import com.playtech.librioniq.web.rest.dto.PostDTO;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entities Post, Question and its DTO QuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionMapper {
    QuestionDTO questionToQuestionDTO(Question question);

    Question questionDTOToQuestion(QuestionDTO questionDTO);
}

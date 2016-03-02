package com.playtech.librioniq.web.rest.mapper;

import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.web.rest.dto.PostDTO;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionMapper {
    QuestionDTO postToQuestionDTO(Post post);

    Post questionDTOToPost(QuestionDTO questionDTO);
}

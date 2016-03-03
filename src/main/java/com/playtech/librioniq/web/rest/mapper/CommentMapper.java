package com.playtech.librioniq.web.rest.mapper;

import com.playtech.librioniq.domain.Comment;
import com.playtech.librioniq.web.rest.dto.CommentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entities Post, Question and its DTO QuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommentMapper {
    CommentDTO commentToCommentDTO(Comment comment);

    Comment commentDTOToComment(CommentDTO commentDTO);
}

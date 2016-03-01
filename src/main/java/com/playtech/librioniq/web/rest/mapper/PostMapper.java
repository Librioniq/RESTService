package com.playtech.librioniq.web.rest.mapper;

import com.playtech.librioniq.domain.*;
import com.playtech.librioniq.web.rest.dto.PostDTO;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Component
@Mapper(componentModel = "spring", uses = {})
public interface PostMapper {

    PostDTO postToPostDTO(Post post);

    Post postDTOToPost(PostDTO postDTO);
}

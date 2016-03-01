package com.playtech.librioniq.web.rest.mapper.impl;

import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.web.rest.dto.PostDTO;
import com.playtech.librioniq.web.rest.mapper.PostMapper;
import org.springframework.stereotype.Component;

/**
 * Created by olehd on 01.03.2016.
 */
@Component
public class PostMapperImpl implements PostMapper {
    @Override
    public PostDTO postToPostDTO(Post post) {
        return new PostDTO();
    }

    @Override
    public Post postDTOToPost(PostDTO postDTO) {
        return new Post();
    }
}

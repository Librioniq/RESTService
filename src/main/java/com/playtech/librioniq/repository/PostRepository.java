package com.playtech.librioniq.repository;

import com.playtech.librioniq.domain.Post;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Post entity.
 */
public interface PostRepository extends JpaRepository<Post,Long> {

}

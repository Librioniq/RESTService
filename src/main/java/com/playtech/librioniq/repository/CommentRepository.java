package com.playtech.librioniq.repository;

import com.playtech.librioniq.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Post entity.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

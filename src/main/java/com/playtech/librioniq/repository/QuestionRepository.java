package com.playtech.librioniq.repository;

import com.playtech.librioniq.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Post entity.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
